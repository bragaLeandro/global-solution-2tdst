package br.com.fiap.service;

import br.com.fiap.constants.CommonConstants;
import br.com.fiap.controller.RecipeController;
import br.com.fiap.dto.IngredientDto;
import br.com.fiap.dto.RecipeCreationDto;
import br.com.fiap.dto.RecipeDto;
import br.com.fiap.entity.*;
import br.com.fiap.repository.IngredientRepository;
import br.com.fiap.repository.RecipeRepository;
import br.com.fiap.repository.UserRecipeRepository;
import br.com.fiap.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final static Logger logger = LoggerFactory.getLogger(RecipeService.class);
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRecipeRepository userRecipeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final GptService gptService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository,
                         UserRecipeRepository userRecipeRepository,
                         UserRepository userRepository,
                         ModelMapper modelMapper,
                         GptService gptService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRecipeRepository = userRecipeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gptService = gptService;

    }

    public RecipeDto createRecipe(RecipeCreationDto recipeCreationDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipeCreationDto.setUser(user);

        RecipeDto recipe = gptService.sendMessageGpt(recipeCreationDto);

        this.saveRecipe(recipe, recipeCreationDto.getUser().getId());
        return recipe;
    }

    public List<RecipeDto> getRecipesPaginated(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, CommonConstants.PAGE_SIZE);
        Page<Recipe> recipes = recipeRepository.findAll(pageable);

        return recipes.stream()
                .map(recipe -> {
                    RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);

                    List<IngredientDto> ingredientDtos = recipe.getIngredients().stream()
                            .map(ingredient -> {
                                IngredientDto ingredientDto = new IngredientDto();
                                ingredientDto.setName(ingredient.getIngredient().getIngredientName());
                                ingredientDto.setQuantity(ingredient.getQuantity());
                                return ingredientDto;
                            })
                            .collect(Collectors.toList());

                    recipeDto.setIngredients(ingredientDtos);

                    return recipeDto;
                })
                .collect(Collectors.toList());
    }

        public List<RecipeDto> getRecipesByUser(int pageNumber) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("User ID -> {}", user.getId());

        Pageable pageable = PageRequest.of(pageNumber, CommonConstants.PAGE_SIZE);

        Page<UserRecipe> userRecipes = userRecipeRepository.findPaginatedByUser(user, pageable);
        List<Recipe> recipes = this.findRecipeByUserRecipes(userRecipes.getContent());

        return recipes.stream()
                .map(recipe -> {
                    RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);

                    List<IngredientDto> ingredientDtos = recipe.getIngredients().stream()
                            .map(ingredient -> {
                                IngredientDto ingredientDto = new IngredientDto();
                                ingredientDto.setName(ingredient.getIngredient().getIngredientName());
                                ingredientDto.setQuantity(ingredient.getQuantity());
                                return ingredientDto;
                            })
                            .collect(Collectors.toList());

                    recipeDto.setIngredients(ingredientDtos);

                    return recipeDto;
                })
                .collect(Collectors.toList());

    }

    List<Recipe> findRecipeByUserRecipes(List<UserRecipe> userRecipes) {
        logger.info("Calling Service findRecipeByUserRecipes");

        return userRecipes.stream()
                .map(UserRecipe::getRecipe).toList();
    }

    @Transactional
    public void saveRecipe(RecipeDto recipeDto, Long userId) {
        logger.info("Saving recipe {} from userID {}...", recipeDto.getTitle(), userId);
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDto.getTitle());
        recipe.setDifficulty(recipeDto.getDifficulty());
        recipe.setPrepTime(recipeDto.getPreparationTime());
        recipe.setPreparationMethod(recipeDto.getPreparationMethod());
        recipe = recipeRepository.save(recipe);

        for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findIngredientByName(ingredientDto.getName())
                    .orElseGet(() -> {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(ingredientDto.getName());
                        return ingredientRepository.save(newIngredient);
                    });

            IngredientRecipe ingredientRecipe = new IngredientRecipe();
            ingredientRecipe.setRecipe(recipe);
            ingredientRecipe.setIngredient(ingredient);
            ingredientRecipe.setQuantity(ingredientDto.getQuantity());

            recipe.getIngredients().add(ingredientRecipe);
        }

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserRecipe userRecipe = new UserRecipe();
        userRecipe.setUser(user);
        userRecipe.setRecipe(recipe);

        user.getUserRecipes().add(userRecipe);

        recipeRepository.save(recipe);
    }
//    @Transactional
//    public void saveRecipe(RecipeDto recipeDto, Long userId) {
//        logger.info("Saving recipe {} from userID {}...", recipeDto.getTitle(), userId);
//
//        Recipe recipe = createRecipe(recipeDto);
//        addIngredientsToRecipe(recipe, recipeDto);
//        associateRecipeToUser(recipe, userId);
//    }
//
//    private Recipe createRecipe(RecipeDto recipeDto) {
//        Recipe recipe = new Recipe();
//        recipe.setTitle(recipeDto.getTitle());
//        recipe.setDifficulty(recipeDto.getDifficulty());
//        recipe.setPrepTime(recipeDto.getPreparationTime());
//        recipe.setPreparationMethod(recipeDto.getPreparationMethod());
//
//        return recipeRepository.save(recipe);
//    }
//
//    private void addIngredientsToRecipe(Recipe recipe, RecipeDto recipeDto) {
//        for (IngredientDto ingredientDto : recipeDto.getIngredients()) {
//            Ingredient ingredient = ingredientRepository.findIngredientByName(ingredientDto.getName())
//                    .orElseGet(() -> {
//                        Ingredient newIngredient = new Ingredient();
//                        newIngredient.setName(ingredientDto.getName());
//                        return ingredientRepository.save(newIngredient);
//                    });
//
//            IngredientRecipe ingredientRecipe = new IngredientRecipe();
//            ingredientRecipe.setRecipe(recipe);
//            ingredientRecipe.setIngredient(ingredient);
//            ingredientRecipe.setQuantity(ingredientDto.getQuantity());
//
//            recipe.getIngredients().add(ingredientRecipe);
//        }
//
//        recipeRepository.save(recipe);
//    }
//
//    private void associateRecipeToUser(Recipe recipe, Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
//        UserRecipe userRecipe = new UserRecipe();
//        userRecipe.setUser(user);
//        userRecipe.setRecipe(recipe);
//
//        user.getUserRecipes().add(userRecipe);
//    }

}

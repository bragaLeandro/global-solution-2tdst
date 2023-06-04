package br.com.fiap.service;

import br.com.fiap.dto.IngredientDto;
import br.com.fiap.dto.RecipeDto;
import br.com.fiap.entity.*;
import br.com.fiap.repository.IngredientRepository;
import br.com.fiap.repository.RecipeRepository;
import br.com.fiap.repository.UserRecipeRepository;
import br.com.fiap.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRecipeRepository userRecipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository,
                         UserRecipeRepository userRecipeRepository,
                         UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRecipeRepository = userRecipeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Recipe saveRecipe(RecipeDto recipeDto, Long userId) {
        // Saving the Recipe
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDto.getTitulo());
        recipe.setDifficulty(recipeDto.getDificuldade());
        recipe.setPrepTime(recipeDto.getTempoPreparo());
        recipe.setDescription(recipeDto.getModoPreparo());
        recipe = recipeRepository.save(recipe);

        // Saving the Ingredients and associating them with the Recipe
        for (IngredientDto ingredientDto : recipeDto.getIngredientes()) {
            Ingredient ingredient = ingredientRepository.findIngredientByName(ingredientDto.getNome())
                    .orElseGet(() -> {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(ingredientDto.getNome());
                        return ingredientRepository.save(newIngredient);
                    });

            IngredientRecipe ingredientRecipe = new IngredientRecipe();
            ingredientRecipe.setRecipe(recipe);
            ingredientRecipe.setIngredient(ingredient);
            ingredientRecipe.setIngredientQuantity(ingredientDto.getQuantidade());

            recipe.getIngredientRecipes().add(ingredientRecipe);
        }

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserRecipe userRecipe = new UserRecipe();
        userRecipe.setUser(user);
        userRecipe.setRecipe(recipe);

        user.getUserRecipes().add(userRecipe);

        return recipeRepository.save(recipe); // Saving the Recipe with all associations
    }
}

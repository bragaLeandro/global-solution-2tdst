package br.com.fiap.service;

import br.com.fiap.dto.IngredientUsageDto;
import br.com.fiap.entity.Recipe;
import br.com.fiap.entity.User;
import br.com.fiap.entity.UserRecipe;
import br.com.fiap.repository.IngredientRepository;
import br.com.fiap.repository.UserRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private RecipeService recipeService;
    private UserRecipeRepository userRecipeRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(RecipeService recipeService, UserRecipeRepository userRecipeRepository,
                             IngredientRepository ingredientRepository) {
        this.recipeService = recipeService;
        this.userRecipeRepository = userRecipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

//    public List<IngredientUsageDto> findIngredientUsages() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Page[] pageResult = ingredientRepository.findTopThreeIngredients(Pageable.ofSize(3));
//        List<Object[]> content = pageResult.;
////        if (!userRecipes.isEmpty()) {
////            List<Recipe> recipes = recipeService.findRecipeByUserRecipes(userRecipes);
////
////            Map<String, Long> ingredientCount = recipes.stream()
////                    .flatMap(recipe -> recipe.getIngredients().stream())
////                    .map(ingredientRecipe -> ingredientRecipe.getIngredient().getIngredientName())
////                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
////
////            return ingredientCount.entrySet().stream()
////                    .map(entry -> new IngredientUsageDto(entry.getKey(), entry.getValue()))
////                    .collect(Collectors.toList());
////
////        }
////        return null;
//    }
}

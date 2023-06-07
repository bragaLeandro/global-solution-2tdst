package br.com.fiap.service;

import br.com.fiap.repository.IngredientRepository;
import br.com.fiap.repository.UserRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

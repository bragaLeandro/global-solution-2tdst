package br.com.fiap.controller;

import br.com.fiap.dto.RecipeCreationDto;
import br.com.fiap.dto.RecipeDto;
import br.com.fiap.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final static Logger logger = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public RecipeDto createRecipe(@RequestBody @Valid RecipeCreationDto recipeCreationDto) {
        logger.info("Calling Service(GET) /recipe {}", recipeCreationDto.getIngredients());
        return recipeService.createRecipe(recipeCreationDto);
    }

    @GetMapping
    public List<RecipeDto> getRecipesByUser(@RequestParam int pageNumber) {
        logger.info("Calling Service(GET) /recipe");
        return recipeService.getRecipesByUser(pageNumber);
    }

    @GetMapping("/all")
    public List<RecipeDto> getRecipesPaginated(@RequestParam int pageNumber) {
        logger.info("Calling Service(GET) /recipe/all");
        return recipeService.getRecipesPaginated(pageNumber);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> findCountRecipesByUser() {
        logger.info("Calling Service(GET) /recipe/count");
        return ResponseEntity.ok(recipeService.findCountRecipesByUser());
    }
}

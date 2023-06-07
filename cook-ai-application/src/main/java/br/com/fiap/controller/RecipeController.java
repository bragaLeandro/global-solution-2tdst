package br.com.fiap.controller;

import br.com.fiap.dto.RecipeCreationDto;
import br.com.fiap.dto.RecipeDto;
import br.com.fiap.entity.Message;
import br.com.fiap.entity.User;
import br.com.fiap.service.MessageService;
import br.com.fiap.service.RecipeService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public RecipeDto createRecipe(@RequestBody @NotNull RecipeCreationDto recipeCreationDto) {
        logger.info("Calling Service(GET) /recipe {}", recipeCreationDto.getIngredients());
        return recipeService.createRecipe(recipeCreationDto);
    }

    @GetMapping
    public List<RecipeDto> getRecipesByUser() {
        logger.info("Calling Service(GET) /recipe");
        return recipeService.getRecipesByUser();
    }

//    @GetMapping
//    public List<Message> getMessages() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return messageService.findByUserId(user.getId());
//    }


}

package br.com.fiap.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TAB_RECIPE")
@SequenceGenerator(name="recipe", sequenceName = "SQ_TB_RECIPE", allocationSize = 1)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe")
    @Column(name = "nm_id")
    private Long id;

    @Column(name = "ds_title")
    private String title;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "prep_time")
    private String preparationTime;

    @Column(name = "ds_recipe", length = 6000)
    private String preparationMethod;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientRecipe> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<UserRecipe> userRecipes;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPrepTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }



    public List<UserRecipe> getUserRecipes() {
        return userRecipes;
    }

    public void setUserRecipes(List<UserRecipe> userRecipes) {
        this.userRecipes = userRecipes;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public List<IngredientRecipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientRecipe> ingredients) {
        this.ingredients = ingredients;
    }
}

package br.com.fiap.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TAB_RECIPE")
@SequenceGenerator(name="recipe", sequenceName = "SQ_TB_RECIPE", allocationSize = 1)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "prep_time")
    private String prepTime;

    @OneToMany(mappedBy = "recipe")
    private List<IngredientRecipe> ingredientRecipes;

    @OneToMany(mappedBy = "recipe")
    private List<UserRecipe> userRecipes;
}

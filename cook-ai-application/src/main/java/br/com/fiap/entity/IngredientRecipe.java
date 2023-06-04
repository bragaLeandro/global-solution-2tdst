package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAB_INGREDIENT_RECIPE")
@SequenceGenerator(name="ingredientRecipe", sequenceName = "SQ_TB_INGREDIENT_RECIPE", allocationSize = 1)
public class IngredientRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredientRecipe")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_quantity")
    private String ingredientQuantity;
}
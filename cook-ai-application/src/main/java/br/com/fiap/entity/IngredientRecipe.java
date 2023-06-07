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
    @Column(name = "nm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "fk_ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_quantity")
    private String quantity;

    public Long getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
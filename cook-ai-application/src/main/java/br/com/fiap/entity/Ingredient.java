package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ingredient")
@SequenceGenerator(name="ingredient", sequenceName = "SQ_TB_INGREDIENT", allocationSize = 1)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient")
    @Column(name = "id")
    private Long id;

    @Column(name = "ingredient_name")
    private String name;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientRecipe> ingredientRecipes;

    public Long getId() {
        return id;
    }

    public String getIngredientName() {
        return name;
    }

    public void setName(String ingredientName) {
        this.name = ingredientName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<IngredientRecipe> getIngredientRecipes() {
        return ingredientRecipes;
    }

    public void setIngredientRecipes(List<IngredientRecipe> ingredientRecipes) {
        this.ingredientRecipes = ingredientRecipes;
    }
}

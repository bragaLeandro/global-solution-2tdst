package br.com.fiap.repository;

import br.com.fiap.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findIngredientByName(String name);

    //    @Query("select ir.ingredient.name, count(ir.ingredient.id) as quantidade from IngredientRecipe ir" +
    //            " group by ir.ingredient.id order by ir.quantity desc")
    //    Page[] findTopThreeIngredients(Pageable pageable);
}
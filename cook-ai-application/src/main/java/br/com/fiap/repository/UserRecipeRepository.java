package br.com.fiap.repository;

import br.com.fiap.entity.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {
}
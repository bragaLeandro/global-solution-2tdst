package br.com.fiap.repository;

import br.com.fiap.entity.Recipe;
import br.com.fiap.entity.User;
import br.com.fiap.entity.UserRecipe;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {



}
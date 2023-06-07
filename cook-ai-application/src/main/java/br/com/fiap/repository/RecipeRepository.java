package br.com.fiap.repository;

import br.com.fiap.entity.Recipe;
import br.com.fiap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
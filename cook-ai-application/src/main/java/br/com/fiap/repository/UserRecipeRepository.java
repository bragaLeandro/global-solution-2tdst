package br.com.fiap.repository;

import br.com.fiap.entity.User;
import br.com.fiap.entity.UserRecipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {

    Page<UserRecipe> findAllByUser(User user, Pageable pageable);
}
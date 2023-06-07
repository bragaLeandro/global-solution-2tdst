package br.com.fiap.repository;

import br.com.fiap.entity.User;
import br.com.fiap.entity.UserRecipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {

    Page<UserRecipe> findPaginatedByUser(User user, Pageable pageable);
    List<UserRecipe> findAllByUser(User user);

}
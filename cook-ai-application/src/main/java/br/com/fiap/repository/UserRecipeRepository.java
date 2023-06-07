package br.com.fiap.repository;

import br.com.fiap.entity.User;
import br.com.fiap.entity.UserRecipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {

    Page<UserRecipe> findPaginatedByUser(User user, Pageable pageable);
    @Query("select count(r.id) from UserRecipe r where r.user = :user")
    Long findCountRecipesByUser(@Param("user") User user);
}
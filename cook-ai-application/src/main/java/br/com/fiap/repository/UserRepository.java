package br.com.fiap.repository;

import br.com.fiap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select count (u.id) from User u")
    Long findCountUsersRegistered();
}
package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    Page<User> findAll(Pageable pageable);


    @Query("SELECT u FROM User u WHERE u.login LIKE CONCAT(:login, '%')")
    Page<User> findAllByLoginLike(String login,Pageable pageable);


    int deleteByLogin(String login);
}

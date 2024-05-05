package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);
}

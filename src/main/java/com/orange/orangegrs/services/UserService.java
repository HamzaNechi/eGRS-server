package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserByLogin(String login);
}

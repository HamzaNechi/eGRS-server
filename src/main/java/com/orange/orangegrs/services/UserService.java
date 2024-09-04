package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface UserService {
    User findUserByLogin(String login);


    Page<User> findAllUsers(Pageable pageable);

    Page<User> findAllUsersByLogin(String login,Pageable pageable);


    int deleteUserByLogin(String login);


    User updateUser(User user);

    User findUserByEmail(String email);


    User updateUserPassword(String newPassword, User user);
}

package com.orange.orangegrs.services;


import org.springframework.beans.factory.annotation.Autowired;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow();
    }

    @Override
    public User findUserByLogin(String login) throws UsernameNotFoundException{
        return userRepository.findByLogin(login).orElseThrow();
    }
}

package com.orange.orangegrs.services;


import org.springframework.beans.factory.annotation.Autowired;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllUsersByLogin(String login, Pageable pageable) {
        return userRepository.findAllByLoginLike(login, pageable);
    }

    @Override
    @Transactional
    public int deleteUserByLogin(String login) {
        return this.userRepository.deleteByLogin(login);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }


}

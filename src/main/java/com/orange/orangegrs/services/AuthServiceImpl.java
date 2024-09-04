package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.repositories.UserRepository;
import com.orange.orangegrs.utils.AuthResponse;
import com.orange.orangegrs.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService{


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public AuthResponse register(User user) {
        AuthResponse response = new AuthResponse();
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);

            if(newUser != null && !newUser.getLogin().isBlank()){
                response.setUser(newUser);
                response.setMessage("Utilisateur ajouté avec succés");
                response.setStatusCode(201);
            }
        }catch(Exception e){
            response.setError(e.getMessage());
            response.setStatusCode(500);
        }
        return response;
    }



    @Override
    public AuthResponse signin(AuthResponse signinRequest) {
        AuthResponse response = new AuthResponse();
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getLogin(), signinRequest.getPassword()));
            var user = userRepository.findByLogin(signinRequest.getLogin()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            var isAdmin = user.getProfile().getProfileId() == 1;
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setAdmin(isAdmin);
            response.setMessage("Utilisateur connecter correctement");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public AuthResponse refreshToken(AuthResponse refreshTokenRequest) {
        AuthResponse response = new AuthResponse();
        String ourLogin = jwtUtils.extractUserNameFromToken(refreshTokenRequest.getToken());
        User user = userRepository.findByLogin(ourLogin).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfuly refresh token");
        }
        response.setStatusCode(500);
        return response;
    }

    @Override
    public User getUserConnected(String token) {
        String ourLogin = jwtUtils.extractUserNameFromToken(token);
        User user = userRepository.findByLogin(ourLogin).orElseThrow();
        if(jwtUtils.isTokenValid(token, user)){
            return user;
        }
        return null;
    }

    @Override
    public int resetPassword(String password, String email) {
        try{
            User user = userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
}

package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.utils.AuthResponse;

public interface AuthService {
    AuthResponse register(User user);

    AuthResponse signin(AuthResponse signinRequest);


    AuthResponse refreshToken(AuthResponse refreshTokenRequest);


    User getUserConnected(String token);
}

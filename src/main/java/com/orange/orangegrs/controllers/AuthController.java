package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.AuthService;
import com.orange.orangegrs.utils.AuthResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;



    @GetMapping("/user")
    public ResponseEntity getUserConnected(HttpServletRequest request){
        try{
            String token ="";
            // Récupérer l'en-tête Authorization
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extraire le token JWT en supprimant "Bearer " du début de l'en-tête
                token = authorizationHeader.substring(7);
            } else {
                return ResponseEntity.status(403).body("Invalide token");
            }
            return ResponseEntity.ok(authService.getUserConnected(token));
        }catch(ExpiredJwtException jwtException){
            return ResponseEntity.status(403).body(jwtException.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    // il faut corriger cette fonction car malgré la suppression de l'authentification tu peut accéder au plusieur fonc avec ancien token
    @GetMapping("/logout")
    public ResponseEntity logout(){
        try{
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.status(200).body("user déconnecter");
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signUp(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AuthResponse authRequest){
        return ResponseEntity.ok(authService.signin(authRequest));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody AuthResponse authRequest){
        return ResponseEntity.ok(authService.refreshToken(authRequest));
    }
}

package com.orange.orangegrs.controllers;

import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.UserService;
import com.orange.orangegrs.utils.JWTUtils;
import com.orange.orangegrs.utils.email.EmailSenderService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/")
    public Page<User> getAllUsers(@RequestParam int page, @RequestParam int size){
        System.out.println("get all user function"+page);
        Pageable pageable = PageRequest.of(page, size);
        return this.userService.findAllUsers(pageable);
    }


    @GetMapping("/{login}")
    public Page<User> getAllUsersByLogin(@RequestParam int page, @RequestParam int size, @PathVariable String login){
        Pageable pageable = PageRequest.of(page, size);
        return this.userService.findAllUsersByLogin(login,pageable);
    }


    @PostMapping("/update/password")
    public ResponseEntity updateUserPassword(HttpServletRequest request, @RequestBody Map<String, String> body){
        System.out.println("hello update password");
        //extract token from request
        String authHeader = request.getHeader("Authorization");
        String jwtToken = authHeader.substring(7);
        User user = this.userService.findUserByLogin(jwtUtils.extractUserNameFromToken(jwtToken)) ;
        String hashedPassword = passwordEncoder.encode(body.get("password"));
        return ResponseEntity.ok(this.userService.updateUserPassword(hashedPassword, user));
    }




    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody User user){
        System.out.println("utilisateur from frontend to controller op update = "+user.getLogin());
        return ResponseEntity.ok(this.userService.updateUser(user));
    }



    @DeleteMapping("/admin/{login}")
    public ResponseEntity deleteUser(@PathVariable String login){
        if(this.userService.deleteUserByLogin(login) == 1){
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }else{
            return ResponseEntity.ok(0);
        }
    }

}

package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.AuthService;
import com.orange.orangegrs.services.UserService;
import com.orange.orangegrs.utils.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class TestController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserService userService;


    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity sayHello(){
        return ResponseEntity.ok("Hello docker running");
    }


    @PostMapping("/")
    public ResponseEntity sendEmail(@RequestBody Map<String, String> email){
        try{
            User user = this.userService.findUserByEmail(email.get("to"));
            if(user != null){
                emailSenderService.sendEmail(email.get("to"));
                return ResponseEntity.ok("email sended");
            }else{
                return ResponseEntity.ok("User not found");
            }
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestBody Map<String, String> body){
        try{
            this.authService.resetPassword(body.get("password"), body.get("email"));
            return ResponseEntity.ok().body("Mot de passe modifier");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

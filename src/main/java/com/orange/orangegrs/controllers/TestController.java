package com.orange.orangegrs.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class TestController {


    @GetMapping("/")
    public ResponseEntity sayHello(){
        return ResponseEntity.ok("Hello docker running");
    }
}

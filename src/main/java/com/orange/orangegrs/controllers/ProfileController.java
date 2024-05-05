package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.Profile;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    private ProfileService profileService;

    @PostMapping("/")
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile){
        Profile pr = this.profileService.addProfile(profile);
        return ResponseEntity.ok(pr);
    }
}

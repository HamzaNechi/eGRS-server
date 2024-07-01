package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.Profile;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProfileController {


    @Autowired
    private ProfileService profileService;


    @GetMapping("/")
    public ResponseEntity getAllProfile(){
        return ResponseEntity.ok(this.profileService.getAllProfiles());
    }



    @PutMapping("/")
    public ResponseEntity updateProfile(@RequestBody Profile profile){
        return ResponseEntity.ok(this.profileService.updateProfile(profile));
    }
}

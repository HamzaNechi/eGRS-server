package com.orange.orangegrs.controllers;

import com.orange.orangegrs.entities.Site;
import com.orange.orangegrs.services.SiteService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    SiteService siteService;


    @GetMapping(name = "/")
    public ResponseEntity<Object> getAllSites(){
        try{
            return ResponseEntity.ok(this.siteService.findAll());
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }


    @GetMapping("/{code}")
    public ResponseEntity<Object> getAllBySiteCode(@PathVariable String code){
        try{
            return ResponseEntity.ok(this.siteService.findBySiteCode(code));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }
}

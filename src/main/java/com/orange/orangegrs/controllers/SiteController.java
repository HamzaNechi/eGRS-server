package com.orange.orangegrs.controllers;

import com.orange.orangegrs.entities.Site;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.SiteService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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


    @GetMapping("/pages")
    public ResponseEntity getAllSites(@RequestParam int page, @RequestParam int size){
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(this.siteService.findAllSites(pageable));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }



    @GetMapping("/pages/{code}")
    public ResponseEntity getAllBySiteCodePagination(@PathVariable String code, @RequestParam int page, @RequestParam int size){
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(this.siteService.getSitesByRefOrCode(pageable,code));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }



    @GetMapping("/nonvisiter/pages")
    public ResponseEntity getAllSiteNonVisite( @RequestParam int page, @RequestParam int size, @RequestParam Date dateMin){
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(this.siteService.getAllSiteNonPasEncoreUneVisite(pageable, dateMin));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }


    @GetMapping("/visiter/pages")
    public ResponseEntity getAllSiteVisiterSelonPeriod( @RequestParam int page, @RequestParam int size, @RequestParam Date dateMin){
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(this.siteService.getAllSiteVisiterAvecDateFiltre(pageable,dateMin));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }
}

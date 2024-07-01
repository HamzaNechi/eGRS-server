package com.orange.orangegrs.controllers;


import com.orange.orangegrs.services.AlertVisitService;
import com.orange.orangegrs.utils.filtreSpecification.FilterAlertRequest;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
public class AlertVisitController {

    @Autowired
    private AlertVisitService alertVisitService;



    @GetMapping("/")
    public ResponseEntity getAllAlerts(){
        try{
            return ResponseEntity.ok(this.alertVisitService.getAllAlerts());
        }catch(ExpiredJwtException e){
            return ResponseEntity.badRequest().body("Token expired");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/filter")
    public ResponseEntity filtreAlert(@RequestBody FilterAlertRequest filterBody){
        try{
            return ResponseEntity.ok(this.alertVisitService.filterAlerts(filterBody));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Exception filter "+ e.getMessage());
        }

    }



    @GetMapping("/{siteId}")
    public ResponseEntity testerAddAlert(@PathVariable int siteId){
        try{
            this.alertVisitService.alertHandler(siteId);
            return ResponseEntity.ok("En cours de traitemnt");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("zid thabbat");
        }
    }


    @GetMapping("/sans")
    public ResponseEntity testerAddAlertSansFacturationReelle(){
        try{
            this.alertVisitService.alertTypeFactureHandler();
            return ResponseEntity.ok("En cours de traitemnt");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("zid thabbat "+ e.getMessage());
        }
    }
}

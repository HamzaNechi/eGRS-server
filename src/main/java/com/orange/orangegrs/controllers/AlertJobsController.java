package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.AlertJobs;
import com.orange.orangegrs.services.AlertJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jobs")
public class AlertJobsController {


    @Autowired
    private AlertJobsService alertJobsService;


    @PostMapping("/")
    public ResponseEntity addNewJob(@RequestBody AlertJobs alertJobs){
        try{
            return ResponseEntity.ok(this.alertJobsService.addNewAlertJob(alertJobs));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PutMapping("/")
    public ResponseEntity updateActivationJob(){
        try{
            return ResponseEntity.ok(this.alertJobsService.updateAlertJobsActivation((short) 2, false));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

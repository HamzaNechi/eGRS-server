package com.orange.orangegrs.controllers;


import com.orange.orangegrs.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;



    @GetMapping("/alert")
    public ResponseEntity distributionOfAlertWithTypePieChart(){
        return ResponseEntity.ok(this.dashboardService.distributionOfAlertWithType());
    }

    @GetMapping("/consommation")
    public ResponseEntity consommationLineChart(){
        return ResponseEntity.ok(this.dashboardService.getConsumptionSummaryForLastSixMonths());
    }

    @GetMapping("/consommation/type_site")
    public ResponseEntity consommationTypeSiteWidgets(){
        return ResponseEntity.ok(this.dashboardService.getConsommationSelonTypeSite());
    }


    @GetMapping("/consommation/total")
    public ResponseEntity consommationTotalDepuisPremierJanvier(){
        return ResponseEntity.ok(this.dashboardService.consommationDepuisPremierJanvier());
    }
}

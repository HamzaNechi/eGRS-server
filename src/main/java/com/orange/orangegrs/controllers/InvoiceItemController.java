package com.orange.orangegrs.controllers;

import com.orange.orangegrs.entities.InvoiceItems;
import com.orange.orangegrs.services.InvoiceItemService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceItemController {

    @Autowired
    InvoiceItemService invoiceItemService;


    @GetMapping("/{siteId}")
    public ResponseEntity getInvoicesBySite(@PathVariable String siteId){
        int sId = Integer.parseInt(siteId);
        try{
            return ResponseEntity.ok(this.invoiceItemService.findInvoiceItemsBySiteId(sId));
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }





    @GetMapping("/type/{siteId}")
    public ResponseEntity dernierFactureReelle(@PathVariable int siteId){
        System.out.println("hello last invoice");
        int sId = siteId;
        try{
            String district = this.invoiceItemService.getDestrictSiteFromInvoiceItem(sId);
            int lastRInvoice = this.invoiceItemService.derniereFactureReel(sId);
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("district", district);
            jsonResponse.put("invoices", lastRInvoice);
            return ResponseEntity.ok(jsonResponse);
        }catch (ExpiredJwtException jwtE){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token expired");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
        }
    }
}

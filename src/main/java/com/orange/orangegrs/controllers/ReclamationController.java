package com.orange.orangegrs.controllers;


import com.orange.orangegrs.entities.Reclamation;
import com.orange.orangegrs.entities.User;
import com.orange.orangegrs.services.ReclamationService;
import com.orange.orangegrs.services.UserService;
import com.orange.orangegrs.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {


    @Autowired
    private ReclamationService reclamationService;

    @Autowired
    private UserService userService;


    @Autowired
    private JWTUtils jwtUtils;


    @PostMapping ("/")
    public ResponseEntity addNewVisite(HttpServletRequest request, @RequestBody Reclamation reclamation){
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.substring(7);
        User user = this.userService.findUserByLogin(jwtUtils.extractUserNameFromToken(authToken));
        reclamation.setLogin(user);
        return ResponseEntity.ok(this.reclamationService.addNewReclamation(reclamation));
    }


    @GetMapping("/")
    public List<Reclamation> getAllReclamation(){
        return this.reclamationService.listOfReclamation();
    }



    @PostMapping ("/delete")
    public ResponseEntity deleteReclamations(@RequestBody List<Integer> listId){
        try{
            this.reclamationService.deleteAllSelected(listId);
            return ResponseEntity.ok(1);
        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}

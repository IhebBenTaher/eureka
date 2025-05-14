package com.example.authentication.controllers;

import com.example.authentication.entities.Utilisateur;
import com.example.authentication.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Utilisateur user){
        return ResponseEntity.ok(service.registerUser(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String userName, @RequestParam String password){
        return ResponseEntity.ok(service.login(userName, password));
    }
}
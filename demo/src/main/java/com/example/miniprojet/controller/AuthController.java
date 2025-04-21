package com.example.miniprojet.controller;

import com.example.miniprojet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.miniprojet.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return authService.login(user);
    }
}
package com.example.miniprojet.service;

import com.example.miniprojet.model.User;
import com.example.miniprojet.repository.UserRepository;
import com.example.miniprojet.security.JwtUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import java.util.Collections;


@Service
public class AuthService {
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    public ResponseEntity<?> register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("User enregistré");
    }

    public ResponseEntity<?> login(User user) {
        User u = userRepo.findByUsername(user.getUsername()).orElseThrow();
        if (!encoder.matches(user.getPassword(), u.getPassword())) return ResponseEntity.status(401).body("info incorrects");
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
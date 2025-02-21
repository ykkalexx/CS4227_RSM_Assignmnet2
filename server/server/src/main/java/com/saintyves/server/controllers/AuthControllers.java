package com.saintyves.server.controllers;

import com.saintyves.server.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthControllers {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Please login with OAuth2");
    }

    @GetMapping("/success")
    public ResponseEntity<?> success() {
        return ResponseEntity.ok(authService.handleOAuthSuccess());
    }

    @GetMapping("/error")
    public ResponseEntity<?> error() {
        return ResponseEntity.badRequest().body("Authentication failed");
    }
}

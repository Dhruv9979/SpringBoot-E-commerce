package com.Springboot.E_commerce.controllers;

import com.Springboot.E_commerce.model.AutheticationRequest;
import com.Springboot.E_commerce.model.AuthenticationResponse;
import com.Springboot.E_commerce.model.RegisterRequest;
import com.Springboot.E_commerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AutheticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

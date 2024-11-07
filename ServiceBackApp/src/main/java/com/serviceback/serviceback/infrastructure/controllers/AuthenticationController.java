package com.serviceback.serviceback.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceback.serviceback.application.services.auth.AuthenticationService;
import com.serviceback.serviceback.domain.dtos.auth.AuthenticationRequest;
import com.serviceback.serviceback.domain.dtos.auth.AuthenticationResponse;
import com.serviceback.serviceback.domain.dtos.auth.LogoutResponse;
import com.serviceback.serviceback.domain.entities.security.User;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
        System.out.println(jwt);
        return ResponseEntity.ok(authenticationService.validateToken(jwt));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile() {
        return ResponseEntity.ok(authenticationService.findLoggedInUser());
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok(new LogoutResponse("La sesión se cerro exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

}

package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.LoginRequest;
import com.deliverytech.delivery.dto.LoginResponse;
import com.deliverytech.delivery.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para login e registro")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @Operation(summary = "Realiza o login e retorna um token JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}

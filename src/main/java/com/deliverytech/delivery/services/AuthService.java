package com.deliverytech.delivery.services;

import com.deliverytech.delivery.config.security.JwtUtil;
import com.deliverytech.delivery.dto.LoginRequest;
import com.deliverytech.delivery.dto.LoginResponse;
import com.deliverytech.delivery.models.Usuario;
import com.deliverytech.delivery.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        
        Usuario user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        String token = jwtUtil.generateToken(user);
        
        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .nome(user.getNome())
                .role(user.getRole().name())
                .build();
    }
}

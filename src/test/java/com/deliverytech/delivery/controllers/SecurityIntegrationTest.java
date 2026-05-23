package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@org.springframework.test.context.ActiveProfiles("test")
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private com.deliverytech.delivery.repositories.UsuarioRepository usuarioRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
        com.deliverytech.delivery.models.Usuario admin = new com.deliverytech.delivery.models.Usuario();
        admin.setNome("Admin Teste");
        admin.setEmail("admin@delivery.com");
        admin.setSenha(passwordEncoder.encode("123456"));
        admin.setRole(com.deliverytech.delivery.models.Usuario.Role.ADMIN);
        admin.setAtivo(true);
        usuarioRepository.save(admin);
    }

    @Test
    void should_ReturnForbidden_When_AccessingProtectedRouteWithoutToken() throws Exception {
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isForbidden());
    }

    @Test
    void should_LoginSuccessfully_When_CredentialsAreValid() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin@delivery.com", "123456");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.email").value("admin@delivery.com"));
    }

    @Test
    void should_ReturnUnauthorized_When_CredentialsAreInvalid() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin@delivery.com", "wrong_password");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}

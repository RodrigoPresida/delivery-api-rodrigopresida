package com.fat.delivery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProductController {

    @GetMapping("/hello")
    @Operation(summary = "Endpoint de teste inicial", description = "Retorna uma mensagem de boas-vindas do sistema de delivery")
    public String helloWorld() {
        return "Olá Mundo! O sistema de Delivery da FAT está online.";
    }
}

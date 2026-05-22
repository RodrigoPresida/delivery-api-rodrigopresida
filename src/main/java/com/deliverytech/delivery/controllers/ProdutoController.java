package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/restaurante/{restauranteId}")
    @Operation(summary = "Lista todos os produtos de um restaurante")
    public ResponseEntity<List<ProdutoDTO>> findByRestaurante(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(service.findByRestaurante(restauranteId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto por ID")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo produto")
    public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.services.RestauranteService;
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
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes", description = "Endpoints para gerenciamento de restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping
    @Operation(summary = "Lista todos os restaurantes")
    public ResponseEntity<List<RestauranteDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/categoria")
    @Operation(summary = "Busca restaurantes por categoria")
    public ResponseEntity<List<RestauranteDTO>> findByCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca restaurante por ID")
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo restaurante")
    public ResponseEntity<RestauranteDTO> insert(@Valid @RequestBody RestauranteDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um restaurante existente")
    public ResponseEntity<RestauranteDTO> update(@PathVariable Long id, @Valid @RequestBody RestauranteDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um restaurante")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

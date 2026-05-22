package com.deliverytech.delivery.controllers;

import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.services.PedidoService;
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
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    @Operation(summary = "Lista todos os pedidos")
    public ResponseEntity<List<PedidoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Busca pedidos de um cliente")
    public ResponseEntity<List<PedidoDTO>> findByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.findByCliente(clienteId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca pedido por ID")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Realiza um novo pedido")
    public ResponseEntity<PedidoDTO> insert(@Valid @RequestBody PedidoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualiza o status de um pedido")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}

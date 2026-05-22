package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDateTime dataHora;

    @NotBlank(message = "Endereço de entrega é obrigatório")
    private String enderecoEntrega;

    private Double valorTotal;
    private String status;

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "ID do restaurante é obrigatório")
    private Long restauranteId;
}

package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Categoria (tipo de cozinha) é obrigatória")
    private String tipoCozinha;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 10, max = 11, message = "Telefone deve ter entre 10 e 11 dígitos")
    private String telefone;

    @PositiveOrZero(message = "Taxa de entrega deve ser positiva")
    private Double taxaEntrega;

    @Min(value = 10, message = "Tempo mínimo de entrega é 10 min")
    @Max(value = 120, message = "Tempo máximo de entrega é 120 min")
    private Integer tempoEntregaMin;
}

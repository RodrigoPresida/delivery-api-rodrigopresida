package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, message = "Descrição deve ter pelo menos 10 caracteres")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @Max(value = 500, message = "Preço máximo permitido é R$ 500")
    private Double preco;

    private Boolean disponivel;

    @NotNull(message = "ID do restaurante é obrigatório")
    private Long restauranteId;
}

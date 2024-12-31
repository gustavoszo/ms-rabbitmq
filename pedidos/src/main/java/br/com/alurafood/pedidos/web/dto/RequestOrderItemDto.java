package br.com.alurafood.pedidos.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RequestOrderItemDto {

    @NotNull
    @Positive
    private Integer quantity;

    @NotBlank
    private String description;
}

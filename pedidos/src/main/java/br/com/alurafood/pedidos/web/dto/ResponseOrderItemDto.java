package br.com.alurafood.pedidos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ResponseOrderItemDto {

    private Long id;
    private Integer quantity;
    private String description;

}

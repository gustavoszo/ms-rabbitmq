package br.com.alurafood.pedidos.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RequestOrderDto {

    @NotNull
    private List<RequestOrderItemDto> itens;

}

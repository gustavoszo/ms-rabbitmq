package br.com.alurafood.pedidos.web.dto;

import br.com.alurafood.pedidos.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ResponseOrderDto {

    private Long id;
    private LocalDateTime date;
    private Status status;
    private List<ResponseOrderItemDto> itens;

}

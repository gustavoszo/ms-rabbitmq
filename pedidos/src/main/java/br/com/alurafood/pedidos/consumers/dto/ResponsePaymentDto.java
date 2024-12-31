package br.com.alurafood.pedidos.consumers.dto;

import br.com.alurafood.pedidos.entities.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ResponsePaymentDto {

    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expire;
    private String code;
    private PaymentStatus status;
    private Long order;
    private Long paymentType;

}

package br.com.alurafood.pagamentos.web.dto;

import br.com.alurafood.pagamentos.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResponsePaymentDto {

    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expire;
    private String code;
    private Status status;
    private Long order;
    private Long paymentType;

}

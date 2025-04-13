package br.com.alura.avaliacao.consumers.dto;

import br.com.alura.avaliacao.consumers.enums.Status;
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
    private Status status;
    private Long order;
    private Long paymentType;

}

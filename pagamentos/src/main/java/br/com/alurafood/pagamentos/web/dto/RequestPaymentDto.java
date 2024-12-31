package br.com.alurafood.pagamentos.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class RequestPaymentDto {

    @NotNull
    private BigDecimal value;

    @NotBlank
    @Size(min = 5, max = 150)
    private String name;

    @NotBlank
    @Positive
    private String number;

    @NotBlank
    private String expire;

    @NotBlank
    private String code;

    @NotNull
    private Long order;

    @NotNull
    private Long paymentType;


}

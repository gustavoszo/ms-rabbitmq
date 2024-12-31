package br.com.alurafood.pagamentos.entities;

import br.com.alurafood.pagamentos.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @Column(length = 150)
    private String name;

    @Column(length = 20)
    private String number;

    @Column(length = 7)
    private String expire;

    @Column(length = 3)
    private String code;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "payment_type_id")
    private Long paymentType;

}

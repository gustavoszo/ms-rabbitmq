package br.com.alurafood.pagamentos.producers;

import br.com.alurafood.pagamentos.web.dto.ResponsePaymentDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.payment.name}")
    private String exchange;

    public void sendPayment(ResponsePaymentDto paymentDto) {
        rabbitTemplate.convertAndSend(exchange, "", paymentDto);
    }

}

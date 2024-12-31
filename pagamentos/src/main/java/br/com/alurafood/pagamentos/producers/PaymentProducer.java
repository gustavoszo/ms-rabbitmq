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

    @Value("${broker.queue.payment.name}")
    private String queue;

    public void sendPayment(ResponsePaymentDto paymentDto) {
        rabbitTemplate.convertAndSend("", queue, paymentDto);
    }

}

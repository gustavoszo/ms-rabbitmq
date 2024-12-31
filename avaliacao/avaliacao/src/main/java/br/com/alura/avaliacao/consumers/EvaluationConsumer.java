package br.com.alura.avaliacao.consumers;

import br.com.alura.avaliacao.consumers.dto.ResponsePaymentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EvaluationConsumer {

    @RabbitListener(queues = "${broker.queue.evaluation.name}")
    public void listenPayment(ResponsePaymentDto paymentDto) {
        System.out.println("Recebeu a mensagem: " + paymentDto.getOrder());
    }

}

package br.com.alurafood.pedidos.consumers;

import br.com.alurafood.pedidos.consumers.dto.ResponsePaymentDto;
import br.com.alurafood.pedidos.services.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${broker.queue.order.name}")
    public void listenPayment(ResponsePaymentDto paymentDto) {
        System.out.println("Recebeu a mensagem: " + paymentDto.getOrder());
        orderService.updateOrderStatus(paymentDto.getOrder());
    }

}

package br.com.alurafood.pedidos.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.order.name}")
    private String queue;

    @Value("${broker.exchange.payment.name}")
    private String paymentExchange;

    @Bean
    public Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(paymentExchange);
    }

    @Bean
    public Binding bindingPaymentOrder() {
        return BindingBuilder.bind(queue())
                .to(fanoutExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Forma de inicializar o RabbitMQ ao subir a aplicação
    /*
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializeAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
    */
}

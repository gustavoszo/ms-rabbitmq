package br.com.alura.avaliacao.configuration;

import org.springframework.amqp.core.*;
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

    @Value("${broker.queue.evaluation.name}")
    private String queue;

    @Value("${broker.exchange.payment.name}")
    private String paymentExchange;

    @Value("${broker.queue.evaluation-dlq.name}")
    private String queueDlx;

    @Value("${broker.exchange.payment-evaluation-dlx.name}")
    private String paymentExchangeDlx;

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable(queue)
                .deadLetterExchange(paymentExchangeDlx)
                .build();
    }

    @Bean
    public Queue queueDlx() {
        return new Queue(queueDlx, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(paymentExchange);
    }

    @Bean
    public FanoutExchange fanoutExchangeDlx() {
        return new FanoutExchange(paymentExchangeDlx);
    }

    @Bean
    public Binding bindingPaymentEvaluation() {
        return BindingBuilder.bind(queue())
                .to(fanoutExchange());
    }

    @Bean
    public Binding bindingPaymentEvaluationDlx() {
        return BindingBuilder.bind(queueDlx())
                .to(fanoutExchangeDlx());
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

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializeAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

}

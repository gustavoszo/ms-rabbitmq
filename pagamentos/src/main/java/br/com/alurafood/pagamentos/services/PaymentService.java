package br.com.alurafood.pagamentos.services;

import br.com.alurafood.pagamentos.clients.OrderClient;
import br.com.alurafood.pagamentos.entities.Payment;
import br.com.alurafood.pagamentos.entities.enums.Status;
import br.com.alurafood.pagamentos.exceptions.EntityNotFoundException;
import br.com.alurafood.pagamentos.producers.PaymentProducer;
import br.com.alurafood.pagamentos.repositories.PaymentRepository;
import br.com.alurafood.pagamentos.web.dto.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;
    private final OrderClient orderClient;

    @Transactional(readOnly = true)
    public Page<Payment> findAll(Pageable pageable)
    {
        return paymentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Payment findById(Long id)
    {
        return paymentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Payment com id '%s' não encontrado", id)));
    }

    @Transactional
    public Payment create(Payment payment)
    {
        var orderExists = orderClient.orderExists(payment.getOrder());
        if (!orderExists.getExists()) throw new EntityNotFoundException(String.format("Order com id '%s' não encontrado", payment.getOrder()));
        return paymentRepository.save(payment);
    }

    @Transactional
    public void delete(Long id)
    {
        Payment payment = findById(id);
        paymentRepository.delete(payment);
    }

    @Transactional
    public void confirmPayment(Long id) {
        Payment payment = findById(id);
        payment.setStatus(Status.CONFIRMED);

        // envio de mensagem para o Rab bitMQ Broker
        paymentProducer.sendPayment(PaymentMapper.toResponsePaymentDto(payment));
    }

    @Transactional
    public void statusChange(Long id) {
        Payment payment = findById(id);
        payment.setStatus(Status.CONFIRMED_WITHOUT_INTEGRATION);
    }
}

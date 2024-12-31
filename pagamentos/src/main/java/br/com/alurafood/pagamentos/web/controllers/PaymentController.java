package br.com.alurafood.pagamentos.web.controllers;

import br.com.alurafood.pagamentos.entities.Payment;
import br.com.alurafood.pagamentos.services.PaymentService;
import br.com.alurafood.pagamentos.web.dto.RequestPaymentDto;
import br.com.alurafood.pagamentos.web.dto.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<Page<Payment>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(paymentService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody RequestPaymentDto paymentDto) {
        Payment payment = paymentService.create(PaymentMapper.toPayment(paymentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmPayment(@PathVariable Long id){
        paymentService.confirmPayment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

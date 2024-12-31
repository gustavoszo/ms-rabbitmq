package br.com.alurafood.pedidos.web.controllers;

import br.com.alurafood.pedidos.entities.Order;
import br.com.alurafood.pedidos.entities.OrderItem;
import br.com.alurafood.pedidos.repositories.projection.OrderProjection;
import br.com.alurafood.pedidos.services.OrderService;
import br.com.alurafood.pedidos.web.dto.RequestOrderDto;
import br.com.alurafood.pedidos.web.dto.ResponseOrderDto;
import br.com.alurafood.pedidos.web.dto.mapper.OrderItemMapper;
import br.com.alurafood.pedidos.web.dto.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/port")
    public String returnPort(@Value("${local.server.port}") String port){
        return String.format("Requisição respondida pela instância executando na porta %s", port);
    }

    @GetMapping
    public ResponseEntity<Page<OrderProjection>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(orderService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ResponseOrderDto> create(@RequestBody RequestOrderDto orderDto) {
        var order = new Order();
        List<OrderItem> orderItens = OrderItemMapper.toOrderItemList(orderDto.getItens());
        order.setItens(orderItens);

        order = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toResponseOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDto> getById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(OrderMapper.toResponseOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id) {
        orderService.updateOrderStatus(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

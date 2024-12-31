package br.com.alurafood.pedidos.services;

import br.com.alurafood.pedidos.entities.Order;
import br.com.alurafood.pedidos.entities.enums.Status;
import br.com.alurafood.pedidos.exceptions.EntityNotFoundException;
import br.com.alurafood.pedidos.repositories.OrderItemRepository;
import br.com.alurafood.pedidos.repositories.OrderRepository;
import br.com.alurafood.pedidos.repositories.projection.OrderProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order create(Order order)
    {
        order.setDate(LocalDateTime.now());
        order.setStatus(Status.REALIZED);

        orderRepository.save(order);
        for (var item : order.getItens()) {
            item.setOrder(order);
            orderItemRepository.save(item);
        };
        return order;
    }

    @Transactional(readOnly = true)
    public Page<OrderProjection> findAll(Pageable pageable)
    {
        return orderRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id)
    {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Order com id '%s' não encontrado", id)));
    }

    @Transactional
    public void delete(Long id)
    {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    @Transactional
    public Order updateOrderStatus(Long id) {
        Order order = findById(id);
        order.setStatus(Status.PAID);
        return order;
    }

    public Boolean orderExists(Long id)
    {
        var order = orderRepository.findById(id).orElse(null);
        return order != null;
    }
}

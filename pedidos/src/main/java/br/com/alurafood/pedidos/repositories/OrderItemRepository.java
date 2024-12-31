package br.com.alurafood.pedidos.repositories;

import br.com.alurafood.pedidos.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

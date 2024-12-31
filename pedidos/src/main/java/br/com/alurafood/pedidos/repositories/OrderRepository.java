package br.com.alurafood.pedidos.repositories;

import br.com.alurafood.pedidos.entities.Order;
import br.com.alurafood.pedidos.repositories.projection.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o")
    Page<OrderProjection> findAllPageable(Pageable pageable);

}

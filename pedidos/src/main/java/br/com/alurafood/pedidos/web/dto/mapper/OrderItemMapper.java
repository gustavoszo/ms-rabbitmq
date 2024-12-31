package br.com.alurafood.pedidos.web.dto.mapper;

import br.com.alurafood.pedidos.entities.OrderItem;
import br.com.alurafood.pedidos.web.dto.RequestOrderItemDto;
import br.com.alurafood.pedidos.web.dto.ResponseOrderItemDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static OrderItem toOrderItem(RequestOrderItemDto dto) {
        return new ModelMapper().map(dto, OrderItem.class);
    }

    public static ResponseOrderItemDto toResponseOrderItem(OrderItem orderItem) {
        return new ModelMapper().map(orderItem, ResponseOrderItemDto.class);
    }

    public static List<ResponseOrderItemDto> toResponseOrderItemList(List<OrderItem> orders) {
        return orders.stream()
                .map(i -> toResponseOrderItem(i))
                .collect(Collectors.toList());
    }

    public static List<OrderItem> toOrderItemList(List<RequestOrderItemDto> dto) {
        return dto.stream()
                .map(i -> toOrderItem(i))
                .collect(Collectors.toList());
    }

}

package online.javaclass.bookstore.service;

import online.javaclass.bookstore.service.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAllByOrderId(Long orderId);

    OrderItemDto find(Long id);

    List<OrderItemDto> getAll();

    OrderItemDto create(OrderItemDto dto);

    OrderItemDto update(OrderItemDto dto);

    boolean deleteById(Long id);
}

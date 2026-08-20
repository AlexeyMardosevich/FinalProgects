package online.javaclass.bookstore.data.repository;

import online.javaclass.bookstore.data.entities.OrderItem;

import java.util.List;

public interface OrderItemRepository extends AbstractRepository<Long, OrderItem> {
    List<OrderItem> findAllByOrderId(Long OrderId);
}

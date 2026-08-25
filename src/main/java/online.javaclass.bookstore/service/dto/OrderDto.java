package online.javaclass.bookstore.service.dto;

import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal cost;
    private Order.Status status;
    private List<OrderItem> items;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}

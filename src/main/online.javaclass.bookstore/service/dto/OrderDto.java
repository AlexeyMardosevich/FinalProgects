package service.dto;

import data.entities.Order;
import data.entities.OrderItem;
import data.entities.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal coast;
    private Order.Status status;
    private List<OrderItem> items;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}

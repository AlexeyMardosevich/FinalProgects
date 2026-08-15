package online.javaclass.bookstore.data.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private User user;
    private BigDecimal coast;
    private Status status;
    private List<OrderItem> items;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}

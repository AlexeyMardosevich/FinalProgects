package online.javaclass.bookstore.service.dto;

import lombok.Data;
import online.javaclass.bookstore.data.entities.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private BigDecimal cost;
    private Order.Status status;
    private List<OrderItemDto> items = new ArrayList<>();

}

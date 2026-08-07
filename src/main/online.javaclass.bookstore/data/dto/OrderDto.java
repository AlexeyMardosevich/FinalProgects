package data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Status status;
    private BigDecimal coast;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}

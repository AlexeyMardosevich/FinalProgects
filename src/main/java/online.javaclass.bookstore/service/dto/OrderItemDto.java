package online.javaclass.bookstore.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {
    private Long id;

    private Long orderId;

    private Long bookId;

    private String bookName;

    private Integer quantity;

    private BigDecimal price;

    public BigDecimal getTotal() {
        if (price == null || quantity == null) {
            return BigDecimal.ZERO;
        }

        return price.multiply(
                BigDecimal.valueOf(quantity)
        );
    }
}

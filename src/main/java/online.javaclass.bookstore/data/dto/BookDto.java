package online.javaclass.bookstore.data.dto;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    @NotNull(message = "should be not null")
    private String name;
    @NotNull(message = "should be not null")
    private String author;
    @NotNull(message = "should be not null")
    private BigDecimal price;
}

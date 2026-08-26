package online.javaclass.bookstore.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    @NotBlank(message = "should be not null")
    private String name;
    @NotBlank(message = "should be not null")
    private String author;
    @NotBlank(message = "should be not null")
    private BigDecimal price;
}

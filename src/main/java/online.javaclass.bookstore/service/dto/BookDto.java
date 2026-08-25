package online.javaclass.bookstore.service.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private BigDecimal price;

}

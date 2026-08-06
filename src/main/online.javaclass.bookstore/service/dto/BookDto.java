package service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private BigDecimal price;

}

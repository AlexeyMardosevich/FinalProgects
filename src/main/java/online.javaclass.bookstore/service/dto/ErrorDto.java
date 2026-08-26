package online.javaclass.bookstore.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String type;
    private String message;

    public ErrorDto(String type) {
        this.type = type;
    }
}
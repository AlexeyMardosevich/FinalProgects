package online.javaclass.bookstore.service.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends ClientException {
    private final Errors errors;

    public ValidationException(Errors errors) {
        super("Request validation failed");
        this.errors = errors;
    }
}
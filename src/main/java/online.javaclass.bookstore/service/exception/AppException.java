package online.javaclass.bookstore.service.exception;

import org.springframework.stereotype.Component;

@Component
public class AppException extends RuntimeException{
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}

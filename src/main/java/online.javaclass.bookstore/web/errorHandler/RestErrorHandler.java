package online.javaclass.bookstore.web.errorHandler;

import online.javaclass.bookstore.service.dto.ErrorDto;
import online.javaclass.bookstore.service.dto.ValidationResultDto;
import online.javaclass.bookstore.service.exception.ClientException;
import online.javaclass.bookstore.service.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RestControllerAdvice
public class RestErrorHandler {

    private static final String SERVER_ERROR = "Server Error";
    private static final String SERVER_ERROR_MESSAGE = "Internal server error";

    private static final String CLIENT_ERROR = "Client Error";
    private static final String DEFAULT_CLIENT_ERROR_MESSAGE = "Invalid request";

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResultDto validationError(ValidationException exception) {
        Map<String, List<String>> errors = mapErrors(exception.getErrors());
        return new ValidationResultDto(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResultDto validationError(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = mapErrors(exception.getBindingResult());
        return new ValidationResultDto(errors);
    }

    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto clientError(ClientException exception) {
        String message = exception.getMessage();
        if (message == null || message.isBlank()) {
            message = DEFAULT_CLIENT_ERROR_MESSAGE;
        }
        return new ErrorDto(CLIENT_ERROR, message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto serverError(Exception exception) {
        return new ErrorDto(SERVER_ERROR, SERVER_ERROR_MESSAGE);
    }
    private Map<String, List<String>> mapErrors(Errors errors) {
        return errors.getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                FieldError::getDefaultMessage,
                                Collectors.toList()
                        )
                ));
    }
}

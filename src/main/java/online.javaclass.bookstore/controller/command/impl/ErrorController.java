package online.javaclass.bookstore.controller.command.impl;

import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;


@Controller
@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String applicationError(AppException e, Model model) {
        model.addAttribute("AppException" , e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String applicationError(ResourceAccessException e, Model model) {
        model.addAttribute("ResourceAccessException" , e.getMessage());
        return "error";
    }
}

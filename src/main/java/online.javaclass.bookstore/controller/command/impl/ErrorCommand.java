package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.controller.command.Command;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
       return "jsp/error.jsp";
    }
}

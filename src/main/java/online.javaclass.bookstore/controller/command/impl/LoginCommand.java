package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LoginCommand implements Command {
    private  final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDto userDto = userService.login(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto);
        return "index.jsp";
    }
}

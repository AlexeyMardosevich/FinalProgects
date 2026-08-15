package online.javaclass.bookstore.controller.command.impl;

import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.data.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.dto.UserDto;


@Log4j
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

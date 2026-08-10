package controller.command.impl;

import controller.command.Command;
import data.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.UserService;
import service.dto.UserDto;


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

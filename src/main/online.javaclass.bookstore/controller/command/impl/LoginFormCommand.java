package controller.command.impl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;


@Log4j
public class LoginFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/login.jsp";
    }
}

package controller.command.impl;

import controller.command.Command;
import data.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.UserService;


@Log4j
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
       req.getSession().invalidate();
        return "index.jsp";
    }
}

package online.javaclass.bookstore.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.controller.command.Command;


@Log4j
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
       req.getSession().invalidate();
        return "index.jsp";
    }
}

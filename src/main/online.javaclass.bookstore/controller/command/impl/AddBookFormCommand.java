package controller.command.impl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class AddBookFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jps/add_book.jsp";
    }
}

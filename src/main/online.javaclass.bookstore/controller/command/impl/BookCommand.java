package controller.command.impl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import service.BookService;
import service.dto.BookDto;

import java.util.List;

public class BookCommand implements Command {
    private static final Logger log = LogManager.getLogger(BookCommand.class);
    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        List<BookDto> book = bookService.getAll();
        req.setAttribute("book", book);
        return "jps/book.jsp";
    }
}
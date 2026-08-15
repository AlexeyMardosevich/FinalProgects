package online.javaclass.bookstore.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
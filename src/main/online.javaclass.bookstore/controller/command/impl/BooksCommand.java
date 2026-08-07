package controller.command.impl;


import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import service.BookService;
import service.dto.BookDto;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.util.List;

public class BooksCommand implements Command {
    private static final Logger log = LogManager.getLogger(BooksCommand.class);
    private final BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req){
        List<BookDto> books = bookService.getAll();
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
package online.javaclass.bookstore.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
        PageableDto pageableDto = PagingUtil.getPageable(req);
        books = bookService.getAll(pageableDto);
        req.setAttribute("page", pageableDto.getPage());
        req.setAttribute("totalPages", pageableDto.getTotalPages());
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
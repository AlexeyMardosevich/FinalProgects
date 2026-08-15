package online.javaclass.bookstore.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;

import java.math.BigDecimal;

@Log4j
@RequiredArgsConstructor
public class EditBookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookDto bookDto = new BookDto();
        bookDto.setId(Long.parseLong(req.getParameter("id")));
        bookDto.setName(req.getParameter("name"));
        bookDto.setAuthor(req.getParameter("author"));
        bookDto.setPrice(new BigDecimal(req.getParameter("price")));
        BookDto update =  bookService.update(bookDto);
        req.setAttribute("book", update);
        return "jsp/book.jsp";
    }
}

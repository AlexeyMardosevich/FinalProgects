package controller.command.impl;

import controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import service.BookService;
import service.dto.BookDto;

import java.math.BigDecimal;

@Log4j2
@RequiredArgsConstructor
public class AddBookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookDto bookDto = new BookDto();
        bookDto.setName(req.getParameter("name"));
        bookDto.setAuthor(req.getParameter("author"));
        bookDto.setPrice(new BigDecimal(req.getParameter("price")));
        BookDto created =  bookService.create(bookDto);
        req.setAttribute("book", created);
        return "jsp/book.jsp";
    }
}

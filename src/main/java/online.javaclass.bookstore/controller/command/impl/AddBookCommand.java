package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
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
        BookDto created = bookService.create(bookDto);
        req.setAttribute("book", created);

        return "jsp/book.jsp";
    }
}

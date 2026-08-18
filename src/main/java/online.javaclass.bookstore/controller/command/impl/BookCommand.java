package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Log4j2
@RequiredArgsConstructor
public class BookCommand implements Command {
    private final BookService bookService;


    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        List<BookDto> book = bookService.getAll();
        req.setAttribute("book", book);
        return "jps/book.jsp";
    }
}
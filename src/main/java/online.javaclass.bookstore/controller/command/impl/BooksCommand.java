package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private final BookService bookService;

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
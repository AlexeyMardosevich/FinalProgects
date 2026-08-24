/*
package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.data.dto.PageResponseDto;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        PageableDto pageableDto = PagingUtil.getPageable(req);
        PageResponseDto<BookDto> pageResponse = bookService.getAll(pageableDto);
        req.setAttribute("page", pageResponse.getPage());
        req.setAttribute("pageSize", pageResponse.getPageSize());
        req.setAttribute("totalItems", pageResponse.getTotalItems());
        req.setAttribute("totalPages", pageResponse.getTotalPages());
        req.setAttribute("books", pageResponse.getItems());

        return "jsp/books.jsp";
    }
}*/

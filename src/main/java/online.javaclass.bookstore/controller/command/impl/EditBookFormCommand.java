package online.javaclass.bookstore.controller.command.impl;

import online.javaclass.bookstore.controller.command.Command;
import online.javaclass.bookstore.data.entities.Book;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;


@Log4j
@RequiredArgsConstructor
public class EditBookFormCommand implements Command {
    BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        BookDto bookDto = bookService.find(id);
        req.setAttribute("book", bookDto);
        return "jsp/edit_book.jsp";
    }
}

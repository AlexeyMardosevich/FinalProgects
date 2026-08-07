package controller.command.impl;

import controller.command.Command;
import data.entities.Book;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.BookService;
import service.dto.BookDto;


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

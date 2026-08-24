package online.javaclass.bookstore.controller.command.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    @GetMapping("/{id}")
    private String get(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.find(id);
        model.addAttribute("book", bookDto);
        return "book";
    }

    @GetMapping("/getAll")
    private String getAll(Model model) {
        List<BookDto> bookDto = bookService.getAll();
        model.addAttribute("books", bookDto);
        return "books";
    }

    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "createBookForm";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        BookDto createdBook = bookService.create(bookDto);

        return "redirect:/books/" + createdBook.getId();
    }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.find(id);
        model.addAttribute("book", bookDto);
        return "editBookForm";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") BookDto bookDto) {
        bookDto.setId(id);
        bookService.update(bookDto);
        return "redirect:/books/" + id;
    }

    @PostMapping("/delete/{id}")
    private String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books/getAll";
    }
}
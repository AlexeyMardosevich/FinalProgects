package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookViewController {

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
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createBookForm";
        }
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
    public String editBook(@PathVariable Long id,@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editBookForm";
        }
        bookService.update(bookDto);
        return "redirect:/books/" + id;
    }

    @PostMapping("/delete/{id}")
    private String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books/getAll";
    }
}
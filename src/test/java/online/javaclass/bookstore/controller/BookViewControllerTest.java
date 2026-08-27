package online.javaclass.bookstore.controller;

import online.javaclass.bookstore.controller.command.impl.BookViewController;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.web.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookViewController.class)
@ContextConfiguration(classes = App.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookViewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    void getAll_shouldOpenBooksPage() throws Exception {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("Clean Code");
        when(bookService.getAll())
                .thenReturn(List.of(book));
        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", List.of(book)));
    }

    @Test
    void createForm_shouldOpenCreateBookForm() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createBookForm"))
                .andExpect(model().attributeExists("book"));
    }
}

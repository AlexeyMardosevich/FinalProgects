package online.javaclass.bookstore.controller;

import online.javaclass.bookstore.controller.command.impl.BookRestController;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.web.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
@ContextConfiguration(classes = App.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    void get_shouldReturnBook() throws Exception {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPrice(new BigDecimal("25.50"));
        when(bookService.find(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Clean Code"))
                .andExpect(jsonPath("$.author")
                        .value("Robert Martin"));
        verify(bookService).find(1L);
    }

    @Test
    void create_shouldReturnCreatedBook() throws Exception {
        BookDto createdBook = new BookDto();
        createdBook.setId(5L);
        createdBook.setName("Effective Java");
        createdBook.setAuthor("Joshua Bloch");
        createdBook.setPrice(new BigDecimal("30.00"));
        when(bookService.create(any(BookDto.class)))
                .thenReturn(createdBook);
        String json = """
                {"name": "Effective Java"
                ,"author": "Joshua Bloch"
                ,"price": 30.00}""";

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name")
                        .value("Effective Java"))
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/api/books/5")
                ));
        verify(bookService).create(any(BookDto.class));
    }
}
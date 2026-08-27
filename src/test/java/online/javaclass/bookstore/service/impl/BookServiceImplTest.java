package online.javaclass.bookstore.service.impl;

import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.service.exception.AppException;
import online.javaclass.bookstore.web.App;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = App.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void find_shouldReturnBookDto_whenBookExists() {
        Book book = new Book();
        book.setId(1L);
        book.setName("Clean Code");
        book.setAuthor("Robert Martin");
        book.setPrice(new BigDecimal("25.50"));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookDto result = bookService.find(1L);
        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getName());
        assertEquals("Robert Martin", result.getAuthor());
        assertEquals(new BigDecimal("25.50"), result.getPrice());
        verify(bookRepository).findById(1L);
    }

    @Test
    void find_shouldThrowException_whenBookDoesNotExist() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(AppException.class, () -> bookService.find(99L));
        verify(bookRepository).findById(99L);
    }

    @Test
    void create_shouldSaveBook_whenDataIsValid() {
        BookDto dto = new BookDto();
        dto.setName("Effective Java");
        dto.setAuthor("Joshua Bloch");
        dto.setPrice(new BigDecimal("30.00"));
        when(bookRepository.save(org.mockito.ArgumentMatchers.any(Book.class)))
                .thenAnswer(invocation -> {Book book = invocation.getArgument(0);
                    book.setId(10L);
                    return book;});
        BookDto result = bookService.create(dto);
        assertEquals(10L, result.getId());
        assertEquals("Effective Java", result.getName());
        verify(bookRepository).save(org.mockito.ArgumentMatchers.any(Book.class));
    }
    @Test
    void create_shouldThrowException_whenNameIsBlank() {
        BookDto dto = new BookDto();
        dto.setName("");
        dto.setAuthor("Author");
        dto.setPrice(new BigDecimal("10.00"));
        assertThrows(AppException.class, () -> bookService.create(dto));
        verify(bookRepository, never()).save(org.mockito.ArgumentMatchers.any(Book.class));
    }
}

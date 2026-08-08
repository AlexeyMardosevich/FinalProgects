package service.impl;

import data.dao.BookDao;
import data.entities.Book;
import data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.BookService;
import service.dto.BookDto;
import service.exception.AppException;

import java.util.List;

@Log4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    //private final BookDao bookDao;
    private final BookRepository bookRepository;


    @Override
    public BookDto find(Long id) {
        Book book = bookRepository.find(id);
        if (book == null) {
            throw new AppException("Couldn't find book with id:" + id);
        }
        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream().
                map(this::toDto).
                toList();
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Book book = toEntity(bookDto);
        //должна быть валидация (проверка логина, пароля и т.д)
        Book created = bookRepository.create(book);
        return toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book = toEntity(bookDto);
        Book update = bookRepository.update(book);
        return toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean delete = bookRepository.deleteById(id);
        if (!delete) {
            throw new AppException("Couldn't delete book with id: " + id);
        }
        return delete;
    }

    private BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthor(entity.getAuthor());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    private Book toEntity(BookDto dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}

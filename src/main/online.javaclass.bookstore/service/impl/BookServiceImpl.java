package service.impl;

import data.BookDao;
import data.entities.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.BookService;
import service.dto.BookDto;
import service.exception.AppException;

import java.util.List;

public class BookServiceImpl implements BookService {

    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public BookDto find(Long id) {
        Book book = bookDao.find(id);
        if (book == null) {
            throw new AppException("Couldn't find user with id:" + id);
        }
        return toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll().stream().
                map(this::toDto).
                toList();
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Book book = toEntity(bookDto);
        //должна быть валидация (проверка логина, пароля и т.д)
        Book created = bookDao.create(book);
        return toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book book = toEntity(bookDto);
        Book update = bookDao.update(book);
        return toDto(update);
    }

    @Override
    public void deleteById(Long id) {
        boolean delete = bookDao.deleteById(id);
        if (!delete) {
            throw new AppException("Couldn't delete user with id: " + id);
        }
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
        entity.setId(entity.getId());
        entity.setName(entity.getName());
        entity.setAuthor(entity.getAuthor());
        entity.setPrice(entity.getPrice());
        return entity;
    }
}

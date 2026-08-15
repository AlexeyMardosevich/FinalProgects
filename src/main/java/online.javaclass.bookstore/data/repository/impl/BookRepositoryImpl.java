package online.javaclass.bookstore.data.repository.impl;

import online.javaclass.bookstore.data.dao.BookDao;
import online.javaclass.bookstore.data.dto.BookDto;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.mapper.EntityDtoMapper;

import java.util.List;

import static online.javaclass.bookstore.mapper.EntityDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.EntityDtoMapper.toEntity;

@Log4j
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;

    @Override
    public Book create(Book book) {
        BookDto dto = toDto(book);
        BookDto bookDto = bookDao.create(dto);

        return toEntity(bookDto);
    }

    @Override
    public Book find(Long id) {
        BookDto bookDto = bookDao.find(id);

        return toEntity(bookDto);
    }

    @Override
    public List<Book> getAll(int size, int offset) {
        List<BookDto> bookDtoList = bookDao.getAll(size, offset);

        return bookDtoList.stream()
                .map(EntityDtoMapper::toEntity)
                .toList();
    }

    @Override
    public List<Book> getAll() {
        List<BookDto> bookDtoList = bookDao.getAll();

        return bookDtoList.stream()
                .map(EntityDtoMapper::toEntity)
                .toList();
    }

    @Override
    public Book update(Book book) {
        BookDto dto = toDto(book);
        BookDto bookDto = bookDao.update(dto);

        return toEntity(bookDto);
    }

    @Override
    public int countAll() {
        return bookDao.countAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return bookDao.deleteById(id);
    }
}

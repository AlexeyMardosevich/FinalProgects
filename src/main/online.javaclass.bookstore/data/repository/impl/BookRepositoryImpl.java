package data.repository.impl;

import data.entities.Book;
import data.entities.User;
import data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class BookRepositoryImpl implements BookRepository {

    @Override
    public Book find(Long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public Book create(Book entity) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

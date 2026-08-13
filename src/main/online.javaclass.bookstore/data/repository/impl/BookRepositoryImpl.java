package data.repository.impl;

import data.dao.BookDao;
import data.dto.BookDto;
import data.entities.Book;
import data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private  final BookDao bookDao;

    @Override
    public List<BookDto> getAll(int size, int offset) {
        return bookDao.getAll(size, offset);
    }

    @Override
    public int countAll() {
        public int countAll() {
            return bookDao.countAll();
        }
    }

    @Override
    public BookDto find(Long id) {
        return bookDao.find(id);
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.getAll();
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return bookDao.create(bookDto);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return bookDao.update(bookDto);
    }

    @Override
    public boolean deleteById(Long id) {
        return bookDao.deleteById(id);
    }
}

package data.repository;

import data.dto.BookDto;

import java.util.List;

public interface BookRepository extends AbstractRepository<Long, BookDto>{
    List<BookDto> getAll(int size, int offset);
    int countAll();
}

package data.dao;

import data.dto.BookDto;
import data.dto.UserDto;
import data.entities.Book;

import java.util.List;

public interface BookDao extends AbstractDao<Long, BookDto>{
    List<BookDto> getAll(int size, int offset);
    int countAll();
}

package service;

import service.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto find (Long id);

    List<BookDto> getAll();

    BookDto create(BookDto bookDto);

    BookDto update (BookDto bookDto);


    void deleteById (Long id);
}

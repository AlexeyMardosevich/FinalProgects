package online.javaclass.bookstore.service;

import online.javaclass.bookstore.service.dto.BookDto;

public interface BookService extends AbstractService<Long, BookDto> {

    int countAll();
}

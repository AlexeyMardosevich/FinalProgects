package service;

import service.dto.BookDto;

import java.util.List;

public interface BookService extends AbstractService<Long, BookDto>{
    int countAll();

}

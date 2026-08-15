package online.javaclass.bookstore.service;

import online.javaclass.bookstore.data.dto.PageableDto;

import java.util.List;

public interface AbstractService <K, T>{
    T find(K id);

    List<T> getAll();
    List<T> getAll(PageableDto pageableDto);

    T create (T Dto);

    T update (T Dto);

    boolean deleteById(K id);
}

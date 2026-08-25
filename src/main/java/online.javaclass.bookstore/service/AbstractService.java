package online.javaclass.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractService <K, T>{
    T find(K id);

    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    T create (T Dto);

    T update (T Dto);

    boolean deleteById(K id);
}

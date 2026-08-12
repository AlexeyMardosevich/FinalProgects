package data.repository;

import data.entities.Book;

import java.util.List;

public interface AbstractRepository<K, T> {

    T find(K id);

    List<T> getAll();

    T create (Book entity);

    T update (T entity);

    boolean deleteById(K id);
}

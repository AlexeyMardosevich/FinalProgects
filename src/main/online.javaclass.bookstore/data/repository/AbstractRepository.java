package data.repository;

import java.util.List;

public interface AbstractRepository<K, T> {

    T find();

    List<T> getAll();

    T create (T entity);

    T update (T entity);

    boolean deleteById(K id);
}

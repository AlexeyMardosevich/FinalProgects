package data.dao;

import java.util.List;

public interface AbstractDao<K, T> {

    T find(K id);

    List<T> getAll();

    T create (T entity);

    T update (T entity);

    boolean deleteById(K id);
}

package service;

import java.util.List;

public interface AbstractService <K, T>{
    T find(K id);

    List<T> getAll();

    T create (T entity);

    T update (T entity);

    boolean deleteById(K id);
}

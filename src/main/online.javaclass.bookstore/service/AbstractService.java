package service;

import java.util.List;

public interface AbstractService <K, T>{
    T find(K id);

    List<T> getAll();

    T create (T Dto);

    T update (T Dto);

    boolean deleteById(K id);
}

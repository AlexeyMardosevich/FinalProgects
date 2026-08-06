package data;

import data.entities.Book;

import java.util.List;

public interface BookDao {

    Book find (Long id);

    List <Book> getAll();

    Book create (Book book);

    Book update (Book book);

    boolean deleteById (Long id);
}

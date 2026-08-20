package online.javaclass.bookstore.data.repository.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.repository.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Book create(Book book) {
        manager.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Book find(Long id) {
        return manager.find(Book.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll(int size, int offset) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset cannot be negative");
        }
        return manager.createQuery(" select b from Book b order by b.id ", Book.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return manager.createQuery("from Book ", Book.class).getResultList();
    }

    @Override
    public Book update(Book book) {
        return manager.merge(book);
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        Long count = manager.createQuery(" select count(b) from Book b", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public boolean deleteById(Long id) {
        Book book = manager.find(Book.class, id);
        boolean delete = false;
        if (book != null) {
            manager.remove(book);
            delete = true;
        }
        return delete;
    }
}

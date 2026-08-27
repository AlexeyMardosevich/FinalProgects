package online.javaclass.bookstore.data.repository;

import online.javaclass.bookstore.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

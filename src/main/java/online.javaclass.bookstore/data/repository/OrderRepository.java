package online.javaclass.bookstore.data.repository;

import online.javaclass.bookstore.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

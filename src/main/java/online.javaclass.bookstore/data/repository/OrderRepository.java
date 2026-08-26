package online.javaclass.bookstore.data.repository;

import online.javaclass.bookstore.data.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select distinct o from Order o left join fetch o.user left join fetch o.items items " +
           "left join fetch items.book order by o.id")
    List<Order> getAll();

    @EntityGraph(attributePaths = {"items", "items.book"})
    Optional<Order> findByUser_IdAndStatus(Long userId, Order.Status status);
}

package online.javaclass.bookstore.data.repository;

import online.javaclass.bookstore.data.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrderId(Long orderId);

    @Query("select oi from OrderItem oi left join fetch oi.order left join fetch oi.book order by oi.id")
    List<OrderItem> getAll();

    List<OrderItem> findAllByOrder_Id(Long orderId);

    Optional<OrderItem> findByOrder_IdAndBook_Id(Long orderId, Long bookId);
}

package online.javaclass.bookstore.service;

import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService extends AbstractService<Long, OrderDto> {
    OrderDto getCart(Long userId);

    OrderDto addToCart(Long userId, Long bookId, Integer quantity);

    OrderDto changeCartItem(Long userId, Long bookId, Integer quantity);

    boolean removeFromCart(Long userId, Long bookId);

    OrderDto checkout(Long userId);

    @Override
    Page<OrderDto> getAll(Pageable pageable);
}

package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.data.repository.OrderItemRepository;
import online.javaclass.bookstore.data.repository.OrderRepository;
import online.javaclass.bookstore.service.OrderItemService;
import online.javaclass.bookstore.service.dto.OrderItemDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Override
    public List<OrderItemDto> findAllByOrderId(Long orderId) {
        validateId(orderId, "Order id");

        return orderItemRepository.findAllByOrderId(orderId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto find(Long id) {
        validateId(id, "Order item id");
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException("Couldn't find order item with id: " + id));
        return toDto(orderItem);
    }

    @Override
    public List<OrderItemDto> getAll() {
        return orderItemRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemDto create(OrderItemDto dto) {
        validateDto(dto);

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new AppException("Couldn't find order with id: " + dto.getOrderId()));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new AppException("Couldn't find book with id: " + dto.getBookId()));
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(book.getPrice());
        OrderItem created = orderItemRepository.save(orderItem);
        return toDto(created);
    }

    @Override
    @Transactional
    public OrderItemDto update(OrderItemDto dto) {
        if (dto == null || dto.getId() == null) {
            throw new AppException("Order item id must not be null");
        }
        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new AppException("Quantity must be greater than zero");
        }
        OrderItem orderItem = orderItemRepository.findById(dto.getId())
                .orElseThrow(() -> new AppException("Couldn't find order item with id: " + dto.getId()));
        orderItem.setQuantity(dto.getQuantity());
        if (dto.getBookId() != null && !dto.getBookId().equals(orderItem.getBook().getId())) {
            Book book = bookRepository.findById(dto.getBookId())
                    .orElseThrow(() -> new AppException("Couldn't find book with id: " + dto.getBookId()));
            orderItem.setBook(book);
            orderItem.setPrice(book.getPrice());
        }
        OrderItem updated = orderItemRepository.save(orderItem);
        return toDto(updated);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        validateId(id, "Order item id");
        if (!orderItemRepository.existsById(id)) {
            throw new AppException("Couldn't find order item with id: " + id);
        }
        orderItemRepository.deleteById(id);
        return true;
    }

    private OrderItemDto toDto(OrderItem entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getId());
        }
        if (entity.getBook() != null) {
            dto.setBookId(entity.getBook().getId());
            dto.setBookName(entity.getBook().getName());
        }
        return dto;
    }

    private void validateDto(OrderItemDto dto) {
        if (dto == null) {
            throw new AppException("Order item must not be null");
        }
        validateId(dto.getOrderId(), "Order id");
        validateId(dto.getBookId(), "Book id");
        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new AppException("Quantity must be greater than zero");
        }
    }

    private void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new AppException(fieldName + " must be greater than zero");
        }
    }
}

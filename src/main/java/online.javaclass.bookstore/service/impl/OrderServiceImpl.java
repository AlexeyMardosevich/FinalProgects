package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.data.repository.OrderItemRepository;
import online.javaclass.bookstore.data.repository.OrderRepository;
import online.javaclass.bookstore.data.repository.UserRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto find(Long id) {
        log.debug("Service call: Get order with id = " + id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException("Couldn't find user with id:" + id));
        return toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getCart(Long userId) {
        validateId(userId, "User id");
        Order cart = orderRepository
                .findByUser_IdAndStatus(userId, Order.Status.CART)
                .orElse(null);
        if (cart == null) {
            return createEmptyCartDto(userId);
        }
        return toDto(cart);
    }

    @Override
    @Transactional
    public OrderDto addToCart(Long userId, Long bookId, Integer quantity) {
        validateId(userId, "User id");
        validateId(bookId, "Book id");
        validateQuantity(quantity);
        Order cart = getOrCreateCart(userId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException("Couldn't find book with id: " + bookId));

        OrderItem item = cart.getItems()
                .stream()
                .filter(currentItem -> currentItem.getBook()
                        .getId()
                        .equals(bookId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            item = new OrderItem();
            item.setBook(book);
            item.setQuantity(quantity);
            item.setPrice(book.getPrice());
            cart.addItem(item);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(book.getPrice());
        }
        recalculateCost(cart);
        Order savedCart = orderRepository.save(cart);
        return toDto(savedCart);
    }

    private Order getOrCreateCart(Long userId) {
        return orderRepository
                .findByUser_IdAndStatus(userId, Order.Status.CART)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new AppException("Couldn't find user with id: " + userId));
                    Order cart = new Order();
                    cart.setUser(user);
                    cart.setStatus(Order.Status.CART);
                    cart.setCost(BigDecimal.ZERO);
                    cart.setItems(new ArrayList<>());
                    return orderRepository.save(cart);
                });
    }

    @Override
    @Transactional
    public OrderDto changeCartItem(Long userId, Long bookId, Integer quantity) {
        validateId(userId, "User id");
        validateId(bookId, "Book id");
        validateQuantity(quantity);
        Order cart = getCartEntity(userId);
        OrderItem item = cart.getItems()
                .stream()
                .filter(currentItem -> currentItem.getBook()
                        .getId()
                        .equals(bookId))
                .findFirst()
                .orElseThrow(() -> new AppException("Book is not present in cart"));
        item.setQuantity(quantity);
        recalculateCost(cart);
        return toDto(orderRepository.save(cart));
    }

    @Override
    @Transactional
    public boolean removeFromCart(Long userId, Long bookId) {
        validateId(userId, "User id");
        validateId(bookId, "Book id");
        Order cart = getCartEntity(userId);
        OrderItem item = cart.getItems()
                .stream()
                .filter(currentItem ->
                        currentItem.getBook()
                                .getId()
                                .equals(bookId))
                .findFirst()
                .orElseThrow(() -> new AppException("Book is not present in cart"));
        cart.removeItem(item);
        recalculateCost(cart);
        orderRepository.save(cart);
        return true;
    }

    @Override
    @Transactional
    public OrderDto checkout(Long userId) {
        validateId(userId, "User id");
        Order cart = getCartEntity(userId);
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new AppException("Cart must not be empty");
        }
        for (OrderItem item : cart.getItems()) {
            Book book = bookRepository.findById(item.getBook().getId())
                    .orElseThrow(() ->
                            new AppException("Book not found: " + item.getBook().getId()));
            item.setPrice(book.getPrice());
        }
        recalculateCost(cart);
        cart.setStatus(Order.Status.PENDING);
        return toDto(orderRepository.save(cart));
    }

    private Order getCartEntity(Long userId) {
        return orderRepository
                .findByUser_IdAndStatus(userId, Order.Status.CART)
                .orElseThrow(() -> new AppException("Cart not found for user: " + userId));
    }

    private void recalculateCost(Order order) {
        BigDecimal cost = order.getItems()
                .stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setCost(cost);
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new AppException("Quantity must be greater than zero");
        }
    }

    private void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new AppException(fieldName + " must be greater than zero");
        }
    }

    private OrderDto createEmptyCartDto(Long userId) {
        OrderDto dto = new OrderDto();
        dto.setUserId(userId);
        dto.setStatus(Order.Status.CART);
        dto.setCost(BigDecimal.ZERO);
        dto.setItems(new ArrayList<>());
        return dto;
    }

    @Override
    public List<OrderDto> getAll() {
        log.debug("Service call: Get all orders");
        return orderRepository.findAll()
                .stream()
                .map(ServiceDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        log.debug("Service call: Get orders. Page = {}, page size = {}",
                pageable.getPageNumber(),
                pageable.getPageSize());
        return orderRepository.findAll(pageable)
                .map(ServiceDtoMapper::toDto);
    }

    @Override
    @Transactional
    public OrderDto create(OrderDto dto) {
        log.debug("Service call: Create new order");
        if (dto == null) {
            throw new AppException("Order must not be null");
        }
        // при необходимости добавьте валидацию полей dto здесь
        Order order = toEntity(dto);
        Order created = orderRepository.save(order);
        return toDto(created);
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto dto) {
        if (dto == null || dto.getId() == null) {
            throw new AppException("Order id must not be null");
        }
        log.debug("Service call: Update order with id = {}", dto.getId());
        Order order = orderRepository.findById(dto.getId())
                .orElseThrow(() -> new AppException("Couldn't find order with id: " + dto.getId()));
        if (dto.getUserId() != null && !dto.getUserId().equals(order.getUser().getId())) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new AppException("Couldn't find user with id: " + dto.getUserId()));
            order.setUser(user);
        }
        recalculateCost(order);
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }
        Order updated = orderRepository.save(order);
        return toDto(updated);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.debug("Service call: Delete order with id = {}", id);

        if (id == null) {
            throw new AppException("Order id must not be null");
        }

        if (!orderRepository.existsById(id)) {
            throw new AppException("Couldn't find order with id: " + id);
        }

        orderRepository.deleteById(id);
        return true;
    }
}


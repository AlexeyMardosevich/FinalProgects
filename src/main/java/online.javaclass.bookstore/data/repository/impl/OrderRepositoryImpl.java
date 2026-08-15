package online.javaclass.bookstore.data.repository.impl;

import online.javaclass.bookstore.data.dao.BookDao;
import online.javaclass.bookstore.data.dao.OrderDao;
import online.javaclass.bookstore.data.dao.OrderItemDao;
import online.javaclass.bookstore.data.dao.UserDao;
import online.javaclass.bookstore.data.dto.BookDto;
import online.javaclass.bookstore.data.dto.OrderDto;
import online.javaclass.bookstore.data.dto.OrderItemDto;
import online.javaclass.bookstore.data.dto.UserDto;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.mapper.EntityDtoMapper;
import online.javaclass.bookstore.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;
    private final BookDao bookDao;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Order find(Long id) {
        OrderDto orderDto = orderDao.find(id);
        Order order = new Order();
        entityDtoMapper.toDto(order);
        UserDto userDto = userDao.find(id);
        User user = new User();
        entityDtoMapper.toEntity(userDto);
        order.setUser(user);
        List<OrderItemDto> orderItemDtos = orderItemDao.findAllByOrderId(order.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setId(orderItemDto.getId());
            BookDto bookDto = bookDao.find(id);
            Book book = new Book();
            book.setId(bookDto.getId());
            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            book.setPrice(new BigDecimal(bookDto.getPrice().toString()));
            orderItem.setBook(book);
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        return order;
    }


    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

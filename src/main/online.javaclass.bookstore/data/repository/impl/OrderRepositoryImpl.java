package data.repository.impl;

import data.dao.BookDao;
import data.dao.OrderDao;
import data.dao.OrderItemDao;
import data.dao.UserDao;
import data.dto.BookDto;
import data.dto.OrderDto;
import data.dto.OrderItemDto;
import data.dto.UserDto;
import data.entities.Book;
import data.entities.Order;
import data.entities.OrderItem;
import data.entities.User;
import data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;
    private final BookDao bookDao;

    @Override
    public Order find() {
        OrderDto orderDto = orderDao.find(find().getId());
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCoast(orderDto.getCoast());
        order.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        UserDto userDto = userDao.find(find().getId());
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        order.setUser(user);
        List<OrderItemDto> orderItemDtos = orderItemDao.findAllByOrderId(order.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setId(orderItemDto.getId());
            BookDto bookDto = bookDao.find(find().getId());
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

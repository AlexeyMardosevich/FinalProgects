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
import mapper.EntityDtoMapper;
import data.repository.OrderRepository;
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
    public Order create(Book entity) {
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

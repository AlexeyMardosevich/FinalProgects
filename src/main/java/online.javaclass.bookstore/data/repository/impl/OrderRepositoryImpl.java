package online.javaclass.bookstore.data.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import online.javaclass.bookstore.data.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static online.javaclass.bookstore.mapper.EntityDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.EntityDtoMapper.toEntity;


@Component
@Log4j2
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;
    private final BookDao bookDao;
 /*   private final EntityDtoMapper entityDtoMapper;
    private final ServiceDtoMapper serviceDtoMapper;*/

    @Override
    public Order find(Long id) {
        OrderDto orderDto = orderDao.find(id);
        if (orderDto == null){
            return null;
        }
        Order order = toEntity(orderDto);

        UserDto userDto = userDao.find(id);
        User user = new User();
        toEntity(userDto);
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
        List<OrderDto> orderDtos = orderDao.getAll();

        List<Order> orders = new ArrayList<>();

        for (OrderDto orderDto : orderDtos) {
            orders.add(find(orderDto.getId()));
        }

        return orders;
    }

    @Override
    public Order create(Order order) {
        OrderDto orderDto = toDto(order);
        OrderDto created = orderDao.create(orderDto);

        return find(created.getId());
    }

    @Override
    public Order update(Order order) {
        OrderDto orderDto = toDto(order);
        OrderDto createdOrder = orderDao.create(orderDto);

        for (OrderItem item : order.getItems()) {
            OrderItemDto itemDto = new OrderItemDto();

            itemDto.setOrderId(createdOrder.getId());
            itemDto.setBookId(item.getBook().getId());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice());

            orderItemDao.create(itemDto);
        }

        return find(createdOrder.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }
}

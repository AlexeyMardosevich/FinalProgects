package online.javaclass.bookstore.mapper;

import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.service.dto.BookDto;
import online.javaclass.bookstore.service.dto.OrderDto;
import online.javaclass.bookstore.service.dto.OrderItemDto;
import online.javaclass.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ServiceDtoMapper {

    public static Order toEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setId(orderDto.getId());
        order.setStatus(orderDto.getStatus());
        order.setCost(BigDecimal.ZERO);

        return order;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(User.Role.valueOf(userDto.getRole()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    public static Book toEntity(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());

        return book;
    }

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        if (order.getUser() != null) {
            orderDto.setUserId(order.getUser().getId());
        }
        orderDto.setCost(order.getCost());
        orderDto.setStatus(order.getStatus());
        if (order.getItems() != null) {
            List<OrderItemDto> items = order.getItems()
                    .stream()
                    .map(ServiceDtoMapper::toDto)
                    .collect(Collectors.toList());
            orderDto.setItems(items);
        }
        return orderDto;
    }

    public static OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();

        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        if (item.getOrder() != null) {
            dto.setOrderId(item.getOrder().getId());
        }
        if (item.getBook() != null) {
            dto.setBookId(item.getBook().getId());
            dto.setBookName(item.getBook().getName());
        }
        return dto;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(String.valueOf(user.getRole()));
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());

        return bookDto;
    }
}

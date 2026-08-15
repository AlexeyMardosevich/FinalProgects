package online.javaclass.bookstore.mapper;

import online.javaclass.bookstore.data.dto.OrderDto;
import online.javaclass.bookstore.data.dto.OrderItemDto;
import online.javaclass.bookstore.data.dto.UserDto;
import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.dto.BookDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityDtoMapper {

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(orderDto.getCost());
        orderDto.setId(orderDto.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        return orderDto;
    }

    public static OrderItemDto toDto(OrderItem orderItem, long orderId) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItemDto.getId());
        orderItemDto.setOrderId(orderItemDto.getOrderId());
        orderItemDto.setBookId(orderItemDto.getBookId());
        orderItemDto.setQuantity(orderItemDto.getQuantity());
        orderItemDto.setPrice(orderItemDto.getPrice());
        return orderItemDto;
    }

    public static Book toEntity(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());

        return book;
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

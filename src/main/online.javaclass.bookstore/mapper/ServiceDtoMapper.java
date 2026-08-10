package mapper;

import data.entities.Book;
import data.entities.Order;
import data.entities.User;
import service.dto.BookDto;
import service.dto.OrderDto;
import service.dto.UserDto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ServiceDtoMapper {

    public OrderDto toDto (Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setCoast(BigDecimal.valueOf(0.0));
        orderDto.setUser(order.getUser());
        orderDto.setItems(order.getItems());
        return orderDto;
    }
    public Order toEntity (OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUser(orderDto.getUser());
        order.setCoast(BigDecimal.valueOf(0.0));
        order.setStatus(orderDto.getStatus());
        order.setItems(orderDto.getItems());
        return order;
    }
    public User toEntity (UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
    public UserDto toDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
    public Book toEntity (BookDto bookDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        return book;
    }
    public BookDto toDto (Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }
}

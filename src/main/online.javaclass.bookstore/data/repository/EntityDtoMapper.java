package data.repository;

import data.dto.OrderDto;
import data.dto.OrderItemDto;
import data.dto.UserDto;
import data.entities.Order;
import data.entities.OrderItem;
import data.entities.User;

public class EntityDtoMapper {

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setCoast(orderDto.getCoast());
        orderDto.setId(orderDto.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        return orderDto;
    }

    public OrderItemDto toDto (OrderItem orderItem, long orderId){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItemDto.getId());
        orderItemDto.setOrderId(orderItemDto.getOrderId());
        orderItemDto.setBookId(orderItemDto.getBookId());
        orderItemDto.setQuantity(orderItemDto.getQuantity());
        orderItemDto.setPrice(orderItemDto.getPrice());
        return orderItemDto;
    }
}

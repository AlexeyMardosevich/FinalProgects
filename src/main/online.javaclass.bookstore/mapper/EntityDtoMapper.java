package mapper;

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
    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(orderDto.getCost());
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

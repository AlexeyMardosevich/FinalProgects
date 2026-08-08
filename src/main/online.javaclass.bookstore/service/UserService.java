package service;


import data.entities.User;
import service.dto.UserDto;


public interface UserService extends AbstractService<Long, UserDto> {
    User login (String email, String password);
}

package service;


import service.dto.UserDto;


public interface UserService extends AbstractService<Long, UserDto> {
    UserDto login (String email, String password);
}

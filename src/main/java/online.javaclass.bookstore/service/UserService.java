package online.javaclass.bookstore.service;

import online.javaclass.bookstore.service.dto.UserDto;

public interface UserService extends AbstractService<Long, UserDto> {
    UserDto login (String email, String password);
}

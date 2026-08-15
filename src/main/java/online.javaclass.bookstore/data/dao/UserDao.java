package online.javaclass.bookstore.data.dao;

import online.javaclass.bookstore.data.dto.UserDto;

public interface UserDao extends AbstractDao<Long, UserDto> {

    UserDto findByEmail(String email);
}

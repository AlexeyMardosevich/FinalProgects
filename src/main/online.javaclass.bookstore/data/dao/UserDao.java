package data.dao;

import data.dto.UserDto;


public interface UserDao extends AbstractDao<Long, UserDto> {
    UserDto findByEmail(String email);

}

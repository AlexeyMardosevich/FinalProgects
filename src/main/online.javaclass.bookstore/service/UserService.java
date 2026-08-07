package service;

import service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto find(Long id);

    List<UserDto> getAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    boolean deleteById(Long id);
}

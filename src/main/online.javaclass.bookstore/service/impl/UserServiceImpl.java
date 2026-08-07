package service.impl;

import data.UserDao;
import data.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.exception.AppException;
import service.dto.UserDto;
import service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto find(Long id) {
        User user = userDao.find(id);
        if (user == null) {
            throw new AppException("Couldn't find user with id:" + id);
        }
        return toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().
                map(this::toDto).
                toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = toEntity(userDto);
        //должна быть валидация (проверка логина, пароля и т.д)
        User created = userDao.create(user);
        return toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = toEntity(userDto);
        User update = userDao.update(user);
        return toDto(update);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean delete = userDao.deleteById(id);
        if (!delete) {
            throw new AppException("Couldn't delete user with id: " + id);
        }
        return delete;
    }

    private UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setLastName(dto.getLastName());
        dto.setFirstName(dto.getFirstName());
        dto.setRole(dto.getRole());
        return dto;
    }

    private User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(entity.getId());
        entity.setEmail(entity.getEmail());
        entity.setPassword(entity.getPassword());
        entity.setLastName(entity.getLastName());
        entity.setFirstName(entity.getFirstName());
        entity.setRole(entity.getRole());
        return entity;
    }
}

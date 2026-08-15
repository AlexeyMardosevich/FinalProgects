package online.javaclass.bookstore.data.repository.impl;

import online.javaclass.bookstore.data.dao.UserDao;
import online.javaclass.bookstore.data.dto.UserDto;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.mapper.EntityDtoMapper;
import online.javaclass.bookstore.service.exception.AppException;

import java.util.List;

import static online.javaclass.bookstore.mapper.EntityDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.EntityDtoMapper.toEntity;


@Log4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;

    @Override
    public User findByEmail(String email) {
        UserDto userDto = userDao.findByEmail(email);

        return toEntity(userDto);
    }

    @Override
    public List<User> getAll(int size, int offset) {
        return List.of();
    }

    @Override
    public int countAll() {
        return 0;
    }

    @Override
    public User find(Long id) {
        UserDto userDto = userDao.find(id);

        if (userDto == null) {
            throw new AppException("Couldn't find userDto with id: " + id);
        }

        return toEntity(userDto);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll()
                .stream()
                .map(EntityDtoMapper::toEntity)
                .toList();
    }

    @Override
    public User create(User user) {
        UserDto dto = toDto(user);
        UserDto created = userDao.create(dto);

        if (created == null) {
            throw new AppException("Couldn't create user with id: " + user.getId());
        }

        return toEntity(created);
    }

    @Override
    public User update(User user) {
        UserDto dto = toDto(user);
        UserDto updated = userDao.update(dto);

        if (updated == null) {
            throw new AppException("Couldn't update user with id: " + user.getId());
        }

        return toEntity(updated);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }
}

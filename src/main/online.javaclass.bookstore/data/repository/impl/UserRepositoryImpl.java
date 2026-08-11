package data.repository.impl;

import data.entities.User;
import data.dao.UserDao;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.EntityDtoMapper;
import mapper.ServiceDtoMapper;
import service.exception.AppException;

import java.util.List;


@Log4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final ServiceDtoMapper serviceDtoMapper;

    @Override
    public User login(String email, String password) {
        User user = userRepository.login(email, password);
        if (user == null){
            throw new RuntimeException("no user with email: " + email);
        }if (!user.getPassword().equals(password)){
            throw new RuntimeException("Wrong password for user: " + email);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User find(Long id) {
        User user = userRepository.find(id);

        if (user == null) {
            throw new AppException("Couldn't find user with id: " + id);
        }
        return entityDtoMapper.toDto(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll()
                .stream()
                .map(entityDtoMapper::toDto)
                .toList();
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        User user = serviceDtoMapper.toEntity(user);
        User updated = userRepository.update(user);
        if (updated == null) {
            throw new AppException("Couldn't update user with id: " + user.getId());
        }
        return entityDtoMapper.toDto(updated);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }
}

package data.repository.impl;

import data.entities.User;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.EntityDtoMapper;
import java.util.List;


@Log4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;

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
    public User find(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

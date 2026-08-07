package data.repository.impl;

import data.entities.User;
import data.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User find() {
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

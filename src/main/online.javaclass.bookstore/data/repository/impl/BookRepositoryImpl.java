package data.repository.impl;

import data.entities.User;
import data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class BookRepositoryImpl implements BookRepository {


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

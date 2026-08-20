package online.javaclass.bookstore.data.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        List<User> users = manager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll(int size, int offset) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset cannot be negative");
        }
        return manager.createQuery("select u from User u order by u.id", User.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        Long count = manager.createQuery("select count(u) from User u", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public User find(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User create(User user) {
        manager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return manager.merge(user);
    }

    @Override
    public boolean deleteById(Long id) {
        User user = manager.find(User.class, id);
        boolean delete = false;
        if (user != null) {
            manager.remove(user);
            delete = true;
        }
        return delete;
    }
}

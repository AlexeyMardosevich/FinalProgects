package online.javaclass.bookstore.data.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Log4j2
@RequiredArgsConstructor
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional(readOnly = true)
    public Order find(Long id) {
        return manager.find(Order.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll(int size, int offset) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        if (offset < 0) {throw new IllegalArgumentException("Offset cannot be negative");
        }
        return manager.createQuery("select distinct o from Order o left join fetch o.user left join fetch o.items items " +
                                   "left join fetch items.book order by o.id", Order.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        Long count = manager.createQuery("select count(o) from Order o", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return manager.createQuery("select distinct o from Order o left join fetch o.user left join fetch o.items items " +
                                   "left join fetch items.book order by o.id", Order.class)
                .getResultList();
    }

    @Override
    public Order create(Order order) {
        manager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        return manager.merge(order);
    }

    @Override
    public boolean deleteById(Long id) {
        Order order = manager.find(Order.class, id);
        if (order == null) {
            return false;
        }
        manager.remove(order);
        return true;
    }
}

/*
package online.javaclass.bookstore.data.repository.impl;

import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> findAllByOrderId(Long orderId) {
        return manager.createQuery("select oi from OrderItem oi where oi.order.id = :orderId order by oi.id", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItem find(Long id) {
        return manager.find(OrderItem.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getAll() {
        return manager.createQuery(" select oi from OrderItem oi left join fetch oi.order left join fetch oi.book order by oi.id ", OrderItem.class)
                .getResultList();
    }

    @Override
    public OrderItem create(OrderItem entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public OrderItem update(OrderItem entity) {
        return manager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        OrderItem orderItem = manager.find(OrderItem.class, id);
        boolean delete = false;
        if (orderItem != null){
            manager.remove(orderItem);
            delete = true;
        }
        return delete;
    }
}
*/

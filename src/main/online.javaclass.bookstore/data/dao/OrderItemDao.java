package data.dao;

import data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDao extends AbstractDao<Long, OrderItemDto>{
    List<OrderItemDto> findAllByOrderId(Long OrderId);
}

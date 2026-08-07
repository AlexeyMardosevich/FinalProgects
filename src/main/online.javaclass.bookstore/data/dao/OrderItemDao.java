package data.dao;

import data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDao extends AbstractDao<Long, OrderItemDao>{
    List<OrderItemDto> findAllByOrderId(Long OrderId);
}

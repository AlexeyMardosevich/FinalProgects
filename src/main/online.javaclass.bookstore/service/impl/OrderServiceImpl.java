package service.impl;

import data.entities.Order;
import data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.ServiceDtoMapper;
import service.OrderService;
import service.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

@Log4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ServiceDtoMapper serviceDtoMapper;

    @Override
    public OrderDto find(Long id) {
        log.debug("Service call: Get order with id = " + id);
        try {
            Order order = orderRepository.find(id);
            return serviceDtoMapper.toDto(order);
        } catch (RuntimeException e) {
            throw new RuntimeException("order" + id, e);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        log.debug("Service call: Get all orders");
        List<Order> orderList = orderRepository.getAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(serviceDtoMapper.toDto(order)));
        return orderDtos;
    }

    @Override
    public OrderDto create(OrderDto dto) {
        log.debug("Service call: Create new order");
        Order order = serviceDtoMapper.toEntity(dto);
        try {
            order = orderRepository.create(order);
            return serviceDtoMapper.toDto(order);
        } catch (RuntimeException e) {
            throw new RuntimeException("Order dto", e);
        }
    }

    @Override
    public OrderDto update(OrderDto dto) {
        log.debug("Service call: Update order with id = " + dto.getId());
        Order order = serviceDtoMapper.toEntity(dto);
        //validateStatus(order); валидация
        try {
            order = orderRepository.update(order);
            return serviceDtoMapper.toDto(order);
        } catch (RuntimeException e) {
            throw new RuntimeException("orderDto", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        log.debug("Service call: Delete order with id = " + id);
        if (!orderRepository.deleteById(id)) {
            throw new RuntimeException("order" + id);
        }
        return false;
    }
}


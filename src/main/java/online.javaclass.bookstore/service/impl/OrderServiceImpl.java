package online.javaclass.bookstore.service.impl;

import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toDto;
import static online.javaclass.bookstore.mapper.ServiceDtoMapper.toEntity;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto find(Long id) {
        log.debug("Service call: Get order with id = " + id);
        try {
            Order order = orderRepository.find(id);
            return toDto(order);
        } catch (RuntimeException e) {
            throw new RuntimeException("order" + id, e);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        log.debug("Service call: Get all orders");
        List<Order> orderList = orderRepository.getAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderList.forEach(order -> orderDtos.add(toDto(order)));
        return orderDtos;
    }

    @Override
    public List<OrderDto> getAll(PageableDto pageableDto) {
        return java.util.Collections.emptyList();
    }

    @Override
    public OrderDto create(OrderDto dto) {
        log.debug("Service call: Create new order");
        Order order = toEntity(dto);
        try {
            order = orderRepository.create(order);
            return toDto(order);
        } catch (RuntimeException e) {
            throw new RuntimeException("Order dto", e);
        }
    }

    @Override
    public OrderDto update(OrderDto dto) {
        log.debug("Service call: Update order with id = " + dto.getId());
        Order order = toEntity(dto);
        //validateStatus(order); валидация
        try {
            order = orderRepository.update(order);
            return toDto(order);
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


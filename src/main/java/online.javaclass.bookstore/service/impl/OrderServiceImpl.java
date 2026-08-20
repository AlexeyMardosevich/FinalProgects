package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dto.PageResponseDto;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.repository.OrderRepository;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (order == null) {
                throw new AppException("Couldn't find order with id: " + id);
            }
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
    public PageResponseDto<OrderDto> getAll(PageableDto pageableDto) {
        log.debug("Service call: Get orders. Page = {}, page size = {}", pageableDto.getPage(), pageableDto.getPageSize());

        List<OrderDto> orders = orderRepository
                .getAll(pageableDto.getPageSize(),pageableDto.getOffset())
                .stream()
                .map(order -> toDto(order))
                .collect(Collectors.toList());

        int totalItems = orderRepository.countAll();

        return new PageResponseDto<>(orders, pageableDto.getPage(), pageableDto.getPageSize(), totalItems);
    }

    @Override
    @Transactional
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
    @Transactional
    public OrderDto update(OrderDto dto) {
        log.debug("Service call: Update order with id = " + dto.getId());
        if (dto.getId() == null) {
            throw new AppException("Order id must not be null");
        }
        if (orderRepository.find(dto.getId()) == null) {
            throw new AppException("Couldn't find order with id: " + dto.getId());
        }
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
    @Transactional
    public boolean deleteById(Long id) {
        log.debug("Service call: Delete order with id = " + id);
        if (!orderRepository.deleteById(id)) {
            throw new RuntimeException("Couldn't find order with id: " + id);
        }
        return true;
    }
}


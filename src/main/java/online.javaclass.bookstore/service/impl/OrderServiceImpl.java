package online.javaclass.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.repository.OrderRepository;
import online.javaclass.bookstore.mapper.ServiceDtoMapper;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import online.javaclass.bookstore.service.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new AppException("Couldn't find user with id:" + id));
            return toDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        log.debug("Service call: Get all orders");
        return orderRepository.findAll()
                .stream()
                .map(ServiceDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        log.debug("Service call: Get orders. Page = {}, page size = {}",
                pageable.getPageNumber(),
                pageable.getPageSize());
        return orderRepository.findAll(pageable)
                .map(ServiceDtoMapper::toDto);
    }

    @Override
    @Transactional
    public OrderDto create(OrderDto dto) {
        log.debug("Service call: Create new order");
        if (dto == null) {
            throw new AppException("Order must not be null");
        }
        // при необходимости добавьте валидацию полей dto здесь
        Order order = toEntity(dto);
        Order created = orderRepository.save(order);
        return toDto(created);
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto dto) {
        log.debug("Service call: Update order with id = {}", dto.getId());

        if (dto == null || dto.getId() == null) {
            throw new AppException("Order id must not be null");
        }
        Order order = orderRepository.findById(dto.getId())
                .orElseThrow(() -> new AppException("Couldn't find order with id: " + dto.getId()));
        order.setUser(dto.getUser());
        order.setCost(dto.getCost());
        order.setStatus(dto.getStatus());
        Order updated = orderRepository.save(order);
        return toDto(updated);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.debug("Service call: Delete order with id = {}", id);

        if (id == null) {
            throw new AppException("Order id must not be null");
        }

        if (!orderRepository.existsById(id)) {
            throw new AppException("Couldn't find order with id: " + id);
        }

        orderRepository.deleteById(id);
        return true;
    }
}


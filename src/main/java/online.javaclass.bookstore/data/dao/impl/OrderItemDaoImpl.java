package online.javaclass.bookstore.data.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dao.OrderItemDao;
import online.javaclass.bookstore.data.dto.OrderItemDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    public static final String GET_ORDER_ITEMS = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price " +
                                                 "FROM order_items oi WHERE oi.id = ?";
    private static final String GET_BY_ORDER_ID = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price " +
                                                  "FROM order_items oi WHERE oi.order_id = ? ORDER BY oi.id";
    private static final String GET_ALL = "SELECT oi.id,oi.order_id, oi.book_id, oi.quantity, oi.price " +
                                          "FROM order_items oi ORDER BY oi.id";
    private static final String CREATE = "INSERT INTO order_items(order_id, book_id, quantity, price)" +
                                         " VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE order_items SET order_id = :order_id, book_id = :book_id, quantity = :quantity," +
                                         " price = :price WHERE id = :id";
    private static final String DELETE_BY_ID = "DELETE FROM order_items  WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public OrderItemDto find(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_ORDER_ITEMS, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderItemDto> findAllByOrderId(Long orderId) {
        return jdbcTemplate.query(GET_BY_ORDER_ID, this::mapRow, orderId);
    }

    @Override
    public List<OrderItemDto> getAll() {
        return jdbcTemplate.query(GET_ALL, this::mapRow);
    }

    @Override
    public OrderItemDto create(OrderItemDto orderItem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getBookId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setBigDecimal(4, orderItem.getPrice());
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::find)
                .orElseThrow(() -> new IllegalStateException(
                        "Couldn't get generated id "
                        + "after creating orderItem"));
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItem) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderItem.getId());
        params.put("order_id", orderItem.getOrderId());
        params.put("quantity", orderItem.getQuantity());
        params.put("price", orderItem.getPrice());
        params.put("book_id", orderItem.getBookId());
        int updateRow = namedParameterJdbcTemplate.update(UPDATE, params);
        if (updateRow == 0) {
            return null;
        }
        return find(orderItem.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return 1 == jdbcTemplate.update(DELETE_BY_ID, id);
    }

    private OrderItemDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(resultSet.getLong("id"));
        orderItemDto.setOrderId(resultSet.getLong("order_id"));
        orderItemDto.setQuantity(resultSet.getInt("quantity"));
        orderItemDto.setPrice(resultSet.getBigDecimal("price"));
        orderItemDto.setBookId(resultSet.getLong("book_id"));
        return orderItemDto;
    }
}

/*
package online.javaclass.bookstore.data.dao.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.data.dao.OrderDao;
import online.javaclass.bookstore.data.dto.OrderDto;
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
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    public static final String GET_BY_ID = "SELECT o.id, o.status, o.cost, o.user_id FROM orders o WHERE o.id = ?";
    private static final String GET_ALL = "SELECT o.id, o.status, o.cost, o.user_id FROM orders o ORDER BY o.id";
    private static final String CREATE = "INSERT INTO orders  (status, cost, user_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET status = :status ,cost = :cost,user_id = :user_id WHERE id = :id";
    private static final String DELETE_BY_ID = "DELETE FROM orders o WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public OrderDto find(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderDto> getAll() {
        return jdbcTemplate.query(GET_ALL, this::mapRow);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, orderDto.getStatus().name());
            preparedStatement.setBigDecimal(2, orderDto.getCost());
            preparedStatement.setLong(3, orderDto.getUserId());
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::find)
                .orElseThrow(() -> new IllegalStateException(
                        "Couldn't get generated id "
                        + "after creating order"));
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", orderDto.getId());
        params.put("user_id", orderDto.getUserId());
        params.put("cost", orderDto.getCost());
        params.put("status", orderDto.getStatus().name());
        int updateRow = namedParameterJdbcTemplate.update(UPDATE, params);
        if (updateRow == 0) {
            return null;
        }
        return find(orderDto.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return 1 == jdbcTemplate.update(DELETE_BY_ID, id);
    }

    private OrderDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(resultSet.getLong("id"));
        orderDto.setUserId(resultSet.getLong("user_id"));
        orderDto.setCost(resultSet.getBigDecimal("cost"));
        String status = resultSet.getString("status");
        orderDto.setStatus(OrderDto.Status.valueOf(status));
        return orderDto;
    }
}
*/

package data.dao.impl;

import data.connection.DatabaseManager;
import data.dao.OrderDao;
import data.dto.OrderDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    public static final String GET_BY_ID = "SELECT o.id, o.status, o.cost, o.user_id FROM orders o WHERE id = ?";
    private final DatabaseManager databaseManager;

    @Override
    public OrderDto find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                OrderDto orderDto = new OrderDto();
                orderDto.setId(resultSet.getLong("id"));
                orderDto.setCoast(new BigDecimal(resultSet.getString("cost")));
                orderDto.setStatus(OrderDto.Status.valueOf(resultSet.getString("status")));
                orderDto.setUserId(resultSet.getLong("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<OrderDto> getAll() {
        return List.of();
    }

    @Override
    public OrderDto create(OrderDto entity) {
        return null;
    }

    @Override
    public OrderDto update(OrderDto entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

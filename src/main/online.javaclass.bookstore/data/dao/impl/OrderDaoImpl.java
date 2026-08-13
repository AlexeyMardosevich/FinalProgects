package data.dao.impl;

import data.connection.DatabaseManager;
import data.dao.OrderDao;
import data.dto.OrderDto;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    public static final String GET_BY_ID = "SELECT o.id, o.status, o.cost, o.user_id FROM orders o WHERE id = ?";
    private static final String GET_ALL = "SELECT o.id, o.status, o.cost, o.user_id FROM orders o";
    private static final String CREATE = "INSERT INTO orders  (status, cost, user_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET status = ?,cost = ?,user_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM orders o WHERE o.id = ?";

    private final DatabaseManager databaseManager;

    @Override
    public OrderDto find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find order with id: " + id, e);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        List<OrderDto> orders = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get all orders", e);
        }
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementForInsert(statement, orderDto);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return find(id);
                }
            }
            throw new RuntimeException("Couldn't get generated id for order");
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't create order", e);
        }
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            preparedStatementForUpdate(statement, orderDto);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                return null;
            }
            return find(orderDto.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't update order with id: " + orderDto.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't delete order with id: " + id, e);
        }
    }

    private OrderDto mapRow(ResultSet resultSet) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(resultSet.getLong("id"));
        orderDto.setUserId(resultSet.getLong("user_id"));
        orderDto.setCost(resultSet.getBigDecimal("cost"));
        String status = resultSet.getString("status");
        orderDto.setStatus(OrderDto.Status.valueOf(status));
        return orderDto;
    }

    private void preparedStatementForInsert(PreparedStatement statement, OrderDto orderDto) throws SQLException {
        statement.setString(1, orderDto.getStatus().name());
        statement.setBigDecimal(2, orderDto.getCost());
        statement.setLong(3, orderDto.getUserId()
        );
    }

    private void preparedStatementForUpdate(PreparedStatement statement, OrderDto orderDto) throws SQLException {
        preparedStatementForInsert(statement, orderDto);
        statement.setLong(4, orderDto.getId()
        );
    }
}

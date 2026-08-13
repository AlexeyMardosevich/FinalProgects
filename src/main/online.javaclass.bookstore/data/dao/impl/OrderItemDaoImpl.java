package data.dao.impl;

import data.connection.DatabaseManager;
import data.dao.OrderItemDao;
import data.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    public static final String GET_ORDER_ITEMS = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price " +
                                                 "FROM order_items oi WHERE id = ?";
    private static final String GET_BY_ORDER_ID = "SELECT oi.id, oi.order_id, oi.book_id, oi.quantity, oi.price " +
                                                  "FROM order_items oi WHERE order_id = ?";
    private static final String GET_ALL = "SELECT oi.id,oi.order_id, oi.book_id, oi.quantity, oi.price FROM order_items oi";
    private static final String CREATE = " INSERT INTO order_items(order_id, book_id, quantity, price)" +
                                         " VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE order_items SET order_id = ?, book_id = ?, quantity = ?, price = ? " +
                                         "WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM order_items oi WHERE oi.id = ?";

    private final DatabaseManager databaseManager;

    @Override
    public OrderItemDto find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find order item with id: " + id, e);
        }
        return null;
    }

    @Override
    public List<OrderItemDto> findAllByOrderId(Long orderId) {
        List<OrderItemDto> orderItems = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ORDER_ID)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderItems.add(mapRow(resultSet));
                }
            }
            return orderItems;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find order items by order id: " + orderId, e);
        }
    }

    @Override
    public List<OrderItemDto> getAll() {
        List<OrderItemDto> orderItems = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                orderItems.add(mapRow(resultSet));
            }
            return orderItems;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get all order items", e);
        }
    }

    @Override
    public OrderItemDto create(OrderItemDto orderItem) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementForInsert(statement, orderItem);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(1);
                    return find(generatedId);
                }
            }
            throw new RuntimeException("Couldn't get generated id for order item");
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't create order item", e);
        }
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItem) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            preparedStatementForUpdate(statement, orderItem);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                return null;
            }
            return find(orderItem.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't update order item with id: " + orderItem.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't delete order item with id: " + id, e);
        }
    }

    private OrderItemDto mapRow(ResultSet resultSet) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(resultSet.getLong("id"));
        orderItemDto.setOrderId(resultSet.getLong("order_id"));
        orderItemDto.setQuantity(resultSet.getInt("quantity"));
        orderItemDto.setPrice(resultSet.getBigDecimal("price"));
        orderItemDto.setBookId(resultSet.getLong("book_id"));
        return orderItemDto;
    }

    private void preparedStatementForInsert(PreparedStatement statement, OrderItemDto orderItem) throws SQLException {
        statement.setLong(1, orderItem.getOrderId());
        statement.setLong(2, orderItem.getBookId());
        statement.setInt(3, orderItem.getQuantity());
        statement.setBigDecimal(4, orderItem.getPrice());
    }

    private void preparedStatementForUpdate(PreparedStatement statement, OrderItemDto orderItem) throws SQLException {
        preparedStatementForInsert(statement, orderItem);
        statement.setLong(5, orderItem.getId());
    }
}

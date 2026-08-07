package data.dao.impl;

import data.connection.DatabaseManager;
import data.dao.OrderItemDao;
import data.dto.OrderItemDto;
import data.entities.Book;
import data.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    public static final String GET_ORDER_ITEMS = "SELECT * FROM order_items WHERE id = ?";

    DatabaseManager databaseManager;

    @Override
    public OrderItemDao find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setId(resultSet.getLong("id"));
                orderItemDto.setQuantity(resultSet.findColumn("quantity"));
                orderItemDto.setPrice(new BigDecimal(resultSet.getString("price")));
                orderItemDto.setOrderId(resultSet.getLong("order_id"));
                orderItemDto.setBookId(resultSet.getLong("book_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<OrderItemDto> findAllByOrderId(Long OrderId) {
        return List.of();
    }

    @Override
    public List<OrderItemDao> getAll() {
        return List.of();
    }

    @Override
    public OrderItemDao create(OrderItemDao entity) {
        return null;
    }

    @Override
    public OrderItemDao update(OrderItemDao entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

package online.javaclass.bookstore.data.dao.impl;

import online.javaclass.bookstore.data.dao.UserDao;
import online.javaclass.bookstore.data.dto.UserDto;
import online.javaclass.bookstore.data.connection.DatabaseManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    public static final String GET_ALL_USERS = "SELECT u.id, u.email, u.password, u.role u.first_name, u.last_name FROM users u";
    public static final String GET_USERS = "SELECT u.id, u.email, u.password, u.role u.first_name, u.last_name FROM users u WHERE u.id = ?";
    public static final String ADD_NEW_USER = "INSERT INTO users (email, password, role, first_name, last_name) VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET email = ?, password = ?, role = ?, first_name = ?, last_name = ? WHERE u.Id = ?";
    public static final String DELETE_USER = "DELETE FROM users u WHERE u.id = ?";
    private static final String FIND_BY_EMAIL = "SELECT FROM users u (id, email, password, role, first_name, last_name) WHERE u.email = ?";

    private final DatabaseManager databaseManager;

    @Override
    public UserDto find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user by id: " + id, e);
        }
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user by email: " + email, e);
        }
        return null;
    }

    public List<UserDto> getAll() {
        List<UserDto> userList = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                userList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get all users", e);
        }
        return userList;
    }

    @Override
    public UserDto create(UserDto userDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementForInsert(preparedStatement, userDto);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return find(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Couldn't creat user [" + userDto + "]");
    }

    @Override
    public UserDto update(UserDto userDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatementForUpdate(preparedStatement, userDto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user with id: " + userDto.getId(), e);
        }
        return find(userDto.getId());
    }

    @Override
    public boolean deleteById(Long Id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, Id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user with id: " + Id, e);
        }
    }

    private UserDto mapRow(ResultSet resultSet) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(resultSet.getLong("id"));
        userDto.setEmail(resultSet.getString("email"));
        userDto.setPassword(resultSet.getString("password"));
        userDto.setRole(resultSet.getString("role"));
        userDto.setFirstName(resultSet.getString("first_Name"));
        userDto.setLastName(resultSet.getString("last_Name"));
        return userDto;
    }

    private static void preparedStatementForInsert(PreparedStatement statement, UserDto userDto) throws SQLException {
        statement.setString(1, userDto.getEmail());
        statement.setString(2, userDto.getPassword());
        statement.setString(3, userDto.getRole());
        statement.setString(4, userDto.getFirstName());
        statement.setString(5, userDto.getLastName());
    }

    private static void preparedStatementForUpdate(PreparedStatement preparedStatement, UserDto userDto) throws SQLException {
        preparedStatementForInsert(preparedStatement, userDto);
        preparedStatement.setLong(6, userDto.getId());
    }
}

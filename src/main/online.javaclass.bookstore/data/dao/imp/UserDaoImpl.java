package data.dao.imp;

import data.UserDao;
import data.entities.User;
import data.connection.DatabaseManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.dto.UserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_USERS = "SELECT * FROM users WHERE id = ?";
    public static final String ADD_NEW_USER = "INSERT INTO users (email, password, role, first_name, last_name) VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET email = ?, password = ?, role = ?, first_name = ?, last_name = ? WHERE Id = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private final DatabaseManager databaseManager;

    public UserDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public User find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()){
                userList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User create(User user) {
    try (Connection connection = databaseManager.getconnection();
         PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)){
         preparedStatementForInsert(preparedStatement, user);
         preparedStatement.executeUpdate();
         ResultSet resultSet = preparedStatement.getGeneratedKeys();
         if (resultSet.next()){
             Long id = resultSet.getLong("id");
            return find(id);
         }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    throw new RuntimeException("Couldn't creat user [" + user + "]");
    }

    @Override
    public User update(User user) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatementForUpdate(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return find(user.getId());
    }

    @Override
    public boolean deleteById(Long Id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
            preparedStatement.setLong(1,Id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setFirstName(resultSet.getString("first_Name"));
                user.setLastName(resultSet.getString("last_Name"));
        return user;
    }

    private static int preparedStatementForInsert(PreparedStatement preparedStatement, User user) throws SQLException {
        int index = 1;
        preparedStatement.setString(index++, user.getEmail());
        preparedStatement.setString(index++, user.getPassword());
        preparedStatement.setString(index++, user.getRole());
        preparedStatement.setString(index++, user.getFirstName());
        preparedStatement.setString(index++, user.getLastName());
        return index;
    }
    private static void preparedStatementForUpdate(PreparedStatement preparedStatement, User user) throws SQLException{
        int index = preparedStatementForInsert(preparedStatement, user);
        preparedStatement.setLong(index, user.getId());
    }
}

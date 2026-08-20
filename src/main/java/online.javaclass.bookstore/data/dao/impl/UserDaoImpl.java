/*
package online.javaclass.bookstore.data.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dao.UserDao;
import online.javaclass.bookstore.data.dto.UserDto;
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
public class UserDaoImpl implements UserDao {

    public static final String GET_ALL_USERS = "SELECT u.id, u.email, u.password, u.role, u.first_name, u.last_name FROM users u";
    public static final String GET_USERS = "SELECT u.id, u.email, u.password, u.role u.first_name, u.last_name FROM users u WHERE u.id = ?";
    public static final String ADD_NEW_USER = "INSERT INTO users (email, password, role, first_name, last_name) VALUES (?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET email = :email, password = :password, role = :role," +
                                             " first_name = :first_name, last_name = :last_name WHERE id = :id";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL = "SELECT u.id, u.email, u.password, u.role, u.first_name, u.last_name " +
                                                "FROM users u WHERE u.email = ?";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public UserDto find(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_USERS, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_EMAIL, this::mapRow, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<UserDto> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, this::mapRow);
    }

    @Override
    public UserDto create(UserDto userDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDto.getEmail());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setString(3, userDto.getRole());
            preparedStatement.setString(4, userDto.getFirstName());
            preparedStatement.setString(5, userDto.getLastName());
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::find)
                .orElseThrow(() -> new IllegalStateException(
                        "Couldn't get generated id "
                        + "after creating user"));
    }

    @Override
    public UserDto update(UserDto userDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", userDto.getId());
        params.put("email", userDto.getEmail());
        params.put("password", userDto.getPassword());
        params.put("role", userDto.getRole());
        params.put("first_name", userDto.getFirstName());
        params.put("last_name", userDto.getLastName());
        int updateRow = namedParameterJdbcTemplate.update(UPDATE_USER, params);
        if (updateRow == 0) {
            return null;
        }
        return find(userDto.getId());
    }

    @Override
    public boolean deleteById(Long Id) {
        return 1 == jdbcTemplate.update(DELETE_USER, Id);
    }

    private UserDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(resultSet.getLong("id"));
        userDto.setEmail(resultSet.getString("email"));
        userDto.setPassword(resultSet.getString("password"));
        userDto.setRole(resultSet.getString("role"));
        userDto.setFirstName(resultSet.getString("first_Name"));
        userDto.setLastName(resultSet.getString("last_Name"));
        return userDto;
    }
}
*/

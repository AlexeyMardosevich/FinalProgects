package data.dao.impl;

import data.connection.DatabaseManager;
import data.dao.BookDao;
import data.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    public static final String GET_ALL_BOOKS = "SELECT b.id, b.name, b.author, b.price FROM books b";
    public static final String GET_ALL_BOOKS_PAGE = "SELECT b.id, b.name, b.author, b.price " +
                                                    "FROM books b ORDER BY b.id LIMIT ? OFFSET ?";
    public static final String GET_BOOK = "SELECT b.id, b.name, b.author, b.price FROM books b WHERE b.id = ?";
    public static final String ADD_NEW_BOOK = "INSERT INTO books (name, author,price) VALUES (?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET name = ?, author = ?, price = ? WHERE Id = ?";
    public static final String DELETE_BOOK = "DELETE FROM books b WHERE b.id = ?";
    private static final String COUNT_ALL_BOOKS = "SELECT COUNT(*) FROM books";

    private final DatabaseManager databaseManager;

    @Override
    public int countAll() {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ALL_BOOKS);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't count all books", e);
        }
    }

    @Override
    public BookDto find(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<BookDto> getAll() {
        List<BookDto> bookList = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(BookDaoImpl.GET_ALL_BOOKS);
            while (resultSet.next()) {
                bookList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public List<BookDto> getAll(int limit, int offset) {
        List<BookDto> bookList = new ArrayList<>();
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_BOOKS_PAGE)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementForInsert(preparedStatement, bookDto);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return find(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Couldn't creat book [" + bookDto + "]");
    }

    @Override
    public BookDto update(BookDto bookDto) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
            preparedStatementForUpdate(preparedStatement, bookDto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return find(bookDto.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = databaseManager.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private BookDto mapRow(ResultSet resultSet) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(resultSet.getLong("id"));
        bookDto.setName(resultSet.getString("name"));
        bookDto.setAuthor(resultSet.getString("author"));
        bookDto.setPrice(resultSet.getBigDecimal("price"));
        return bookDto;
    }

    private static void preparedStatementForInsert(PreparedStatement preparedStatement, BookDto bookDto) throws SQLException {
        preparedStatement.setString(1, bookDto.getName());
        preparedStatement.setString(2, bookDto.getAuthor());
        preparedStatement.setBigDecimal(3, bookDto.getPrice());
    }

    private static void preparedStatementForUpdate(PreparedStatement preparedStatement, BookDto bookDto) throws SQLException {
        preparedStatement.setLong(4, bookDto.getId());
    }
}

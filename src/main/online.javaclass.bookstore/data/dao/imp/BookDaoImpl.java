package data.dao.imp;

import data.BookDao;
import data.connection.DatabaseManager;
import data.entities.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    public static final String GET_ALL_BOOKS = "SELECT * FROM books";
    public static final String GET_BOOK = "SELECT * FROM books WHERE id = ?";
    public static final String ADD_NEW_BOOK = "INSERT INTO books (name, author,price) VALUES (?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET name = ?, author = ?, price = ? WHERE Id = ?";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";

    private static final Logger log = LogManager.getLogger(BookDao.class);

    private final DatabaseManager databaseManager;

    public BookDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public Book find(Long id) {
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
public List<Book> getAll() {
    List<Book> bookList = new ArrayList<>();
    try (Connection connection = databaseManager.getconnection();
         Statement statement = connection.createStatement()){
        ResultSet resultSet = statement.executeQuery(BookDaoImpl.GET_ALL_BOOKS);
        while (resultSet.next()){
            bookList.add(mapRow(resultSet));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return bookList;
}

@Override
public Book create(Book book) {
    try (Connection connection = databaseManager.getconnection();
         PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS)){
        preparedStatementForInsert(preparedStatement, book);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            Long id = resultSet.getLong("id");
            return find(id);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    throw new RuntimeException("Couldn't creat book [" + book + "]");
}

@Override
public Book update(Book book) {
    try (Connection connection = databaseManager.getconnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
        preparedStatementForUpdate(preparedStatement, book);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return find(book.getId());
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

private Book mapRow(ResultSet resultSet) throws SQLException {
    Book book = new Book();
    book.setId(resultSet.getLong("id"));
    book.setName(resultSet.getString("name"));
    book.setAuthor(resultSet.getString("author"));
    book.getPrice();

    return book;
}
    private static int preparedStatementForInsert(PreparedStatement preparedStatement, Book book) throws SQLException {
        int index = 1;
        preparedStatement.setString(index++, book.getName());
        preparedStatement.setString(index++, book.getAuthor());
        preparedStatement.setBigDecimal(index++, book.getPrice());
        return index;
    }
    private static void preparedStatementForUpdate(PreparedStatement preparedStatement,Book book) throws SQLException{
        int index = preparedStatementForInsert(preparedStatement, book);
        preparedStatement.setLong(index, book.getId());
    }
}

package online.javaclass.bookstore.data.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.data.dao.BookDao;
import online.javaclass.bookstore.data.dto.BookDto;
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
public class BookDaoImpl implements BookDao {
    public static final String GET_ALL_BOOKS = "SELECT b.id, b.name, b.author, b.price FROM books b";
    public static final String GET_ALL_BOOKS_PAGE = "SELECT b.id, b.name, b.author, b.price " +
                                                    "FROM books b ORDER BY b.id LIMIT ? OFFSET ?";
    public static final String GET_BOOK = "SELECT b.id, b.name, b.author, b.price FROM books b WHERE b.id = ?";
    public static final String ADD_NEW_BOOK = "INSERT INTO books (name, author,price) VALUES (?,?,?)";
    public static final String UPDATE_BOOK_PARAM = "UPDATE books SET name = :name, author = :author, price = :price WHERE id = :id";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    private static final String COUNT_ALL_BOOKS = "SELECT COUNT(*) FROM books";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public int countAll() {
        Integer count = jdbcTemplate.queryForObject(COUNT_ALL_BOOKS, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public BookDto find(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BOOK, this::mapRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BookDto> getAll() {
        return jdbcTemplate.query(GET_ALL_BOOKS, this::mapRow);
    }

    @Override
    public List<BookDto> getAll(int limit, int offset) {
        validatePagination(limit, offset);
        return jdbcTemplate.query(GET_ALL_BOOKS_PAGE, this::mapRow, limit, offset);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bookDto.getName());
            preparedStatement.setString(2, bookDto.getAuthor());
            preparedStatement.setBigDecimal(3, bookDto.getPrice());
            return preparedStatement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::find)
                .orElseThrow(() -> new IllegalStateException(
                        "Couldn't get generated id "
                        + "after creating book"));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", bookDto.getId());
        params.put("name", bookDto.getName());
        params.put("author", bookDto.getAuthor());
        params.put("price", bookDto.getPrice());
        int updateRow = namedParameterJdbcTemplate.update(UPDATE_BOOK_PARAM, params);
        if (updateRow == 0) {
            return null;
        }
        return find(bookDto.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return 1 == jdbcTemplate.update(DELETE_BOOK, id);
    }

    private static void validatePagination(int limit, int offset) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset cannot be negative");
        }
    }

    private BookDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setId(resultSet.getLong("id"));
        bookDto.setName(resultSet.getString("name"));
        bookDto.setAuthor(resultSet.getString("author"));
        bookDto.setPrice(resultSet.getBigDecimal("price"));

        return bookDto;
    }
}

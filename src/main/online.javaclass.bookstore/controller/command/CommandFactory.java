package controller.command;

import controller.command.impl.*;
import data.BookDao;
//import data.connection.DataSource;
import data.connection.DatabaseManager;
import data.dao.imp.BookDaoImpl;
import service.BookService;
import service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> map;

    public static final CommandFactory INSTANCE = new CommandFactory();
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "root";
    private CommandFactory() {
        DatabaseManager databaseManager = new DatabaseManager(URL, LOGIN,PASSWORD);
        BookDao bookDao = new BookDaoImpl(databaseManager);
        BookService bookService = new BookServiceImpl(bookDao);
        map = new HashMap<>();
        map.put("book", new BookCommand(bookService));
        map.put("books", new BooksCommand(bookService));
        map.put("error", new ErrorCommand());
        map.put("add_book_form", new AddBookFormCommand());
        map.put("add_book", new AddBookCommand(bookService));
    }

    public Command getController(String command) {
        return map.get(command);
    }
}

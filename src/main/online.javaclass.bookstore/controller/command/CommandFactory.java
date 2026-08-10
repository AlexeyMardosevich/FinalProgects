package controller.command;

import controller.command.impl.*;
import data.connection.DatabaseManager;
import data.repository.BookRepository;
import data.repository.UserRepository;
import data.repository.impl.BookRepositoryImpl;
import data.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.BookService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;
import java.util.HashMap;
import java.util.Map;


@Log4j
@RequiredArgsConstructor
public class CommandFactory {
    private final Map<String, Command> map;

    public static final CommandFactory INSTANCE = new CommandFactory();
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "root";
    private CommandFactory() {
        DatabaseManager databaseManager = new DatabaseManager(URL, LOGIN,PASSWORD);
        BookRepository bookRepository = new BookRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        BookService bookService = new BookServiceImpl(bookRepository);
        UserService userService = new UserServiceImpl(userRepository);
        map = new HashMap<>();
        map.put("book", new BookCommand(bookService));
        map.put("books", new BooksCommand(bookService));
        map.put("error", new ErrorCommand());
        map.put("add_book_form", new AddBookFormCommand());
        map.put("add_book", new AddBookCommand(bookService));
        map.put("login", new LoginCommand(userService));
        map.put("login_form_command", new LoginFormCommand());
        map.put("logout_command", new LogoutCommand());
    }

    public Command getController(String command) {
        return map.get(command);
    }
}

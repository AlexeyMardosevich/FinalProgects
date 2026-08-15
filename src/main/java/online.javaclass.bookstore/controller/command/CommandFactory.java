package online.javaclass.bookstore.controller.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import online.javaclass.bookstore.controller.command.impl.AddBookCommand;
import online.javaclass.bookstore.controller.command.impl.AddBookFormCommand;
import online.javaclass.bookstore.controller.command.impl.BookCommand;
import online.javaclass.bookstore.controller.command.impl.BooksCommand;
import online.javaclass.bookstore.controller.command.impl.ErrorCommand;
import online.javaclass.bookstore.controller.command.impl.LoginCommand;
import online.javaclass.bookstore.controller.command.impl.LoginFormCommand;
import online.javaclass.bookstore.controller.command.impl.LogoutCommand;
import online.javaclass.bookstore.data.connection.DatabaseManager;
import online.javaclass.bookstore.data.dao.BookDao;
import online.javaclass.bookstore.data.dao.UserDao;
import online.javaclass.bookstore.data.dao.impl.BookDaoImpl;
import online.javaclass.bookstore.data.dao.impl.UserDaoImpl;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.data.repository.UserRepository;
import online.javaclass.bookstore.data.repository.impl.BookRepositoryImpl;
import online.javaclass.bookstore.data.repository.impl.UserRepositoryImpl;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.DigestService;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.impl.BookServiceImpl;
import online.javaclass.bookstore.service.impl.DigestServiceImpl;
import online.javaclass.bookstore.service.impl.UserServiceImpl;

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
        DatabaseManager databaseManager = new DatabaseManager(URL, LOGIN, PASSWORD);

        BookDao bookDao = new BookDaoImpl(databaseManager);
        BookRepository bookRepository = new BookRepositoryImpl(bookDao);
        BookService bookService = new BookServiceImpl(bookRepository);

        UserDao userDao = new UserDaoImpl(databaseManager);
        UserRepository userRepository = new UserRepositoryImpl(userDao);

        DigestService digestService = new DigestServiceImpl();
        UserService userService = new UserServiceImpl(userRepository, digestService);

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

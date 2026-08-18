package online.javaclass.bookstore;

import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.DigestService;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.impl.BookServiceImpl;
import online.javaclass.bookstore.service.impl.DigestServiceImpl;
import online.javaclass.bookstore.service.impl.OrderServiceImpl;
import online.javaclass.bookstore.service.impl.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            BookService bookService = context.getBean(BookServiceImpl.class);
            UserService userService = context.getBean(UserServiceImpl.class);
            OrderService orderService = context.getBean(OrderServiceImpl.class);
            DigestService digestService = context.getBean(DigestServiceImpl.class);
            bookService.getAll();
            userService.getAll();
            orderService.getAll();
            digestService.getClass();
        }
    }
}
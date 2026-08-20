package online.javaclass.bookstore;

import online.javaclass.bookstore.data.entities.Book;
import online.javaclass.bookstore.data.entities.Order;
import online.javaclass.bookstore.data.entities.OrderItem;
import online.javaclass.bookstore.data.entities.User;
import online.javaclass.bookstore.data.repository.BookRepository;
import online.javaclass.bookstore.data.repository.OrderItemRepository;
import online.javaclass.bookstore.data.repository.OrderRepository;
import online.javaclass.bookstore.data.repository.UserRepository;
import online.javaclass.bookstore.service.BookService;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class Main {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            BookService bookService = context.getBean(BookService.class);
            BookRepository bookRepository = context.getBean(BookRepository.class);

            UserService userService = context.getBean(UserService.class);
            UserRepository userRepository = context.getBean(UserRepository.class);

            OrderService orderService = context.getBean(OrderService.class);
            OrderRepository orderRepository = context.getBean(OrderRepository.class);

            OrderItemRepository orderItemRepository = context.getBean(OrderItemRepository.class);
            bookService.getAll();
            userService.getAll();
            orderService.getAll();
            System.out.println("USER");
            User user = userRepository.find(1L);
            System.out.println(user);
            System.out.println("BOOK");
            List<Book> book = bookRepository.getAll();
            System.out.println(book.toString());
            System.out.println("ORDER");
            Order order = orderRepository.find(1L);
            System.out.println(order);
            System.out.println("ORDERITEM");
            OrderItem orderItem = orderItemRepository.find(1L);
            System.out.println(orderItem);
        }
    }
}
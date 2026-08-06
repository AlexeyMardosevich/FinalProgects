package org.example.main;

import controller.command.impl.UserController;
import data.connection.DatabaseManager;
import data.UserDao;
import data.dao.imp.UserDaoImpl;
import data.entities.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class Main {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String LOGIN = "postgres";
    public static final String PASSWORD = "root";

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager(URL,PASSWORD,LOGIN);
        UserDao userDao = new UserDaoImpl(databaseManager);
        UserService service = new UserServiceImpl(userDao);
        UserController controller = new UserController(service);


        System.out.println("Start");
        userDao.deleteById(22L);
        System.out.println("----------");
        userDao.find(1L);
        System.out.println("----------");
        userDao.getAll();
        System.out.println("----------");
        User user = new User ();
        user.setEmail("ivan.com");
        user.setPassword("P@ssw0rd");
        user.setFirstName("IvanCreate");
        user.setLastName("PetrovCreate");
        user.setRole("user");
        userDao.create(user);
        System.out.println("----------");
    }
}

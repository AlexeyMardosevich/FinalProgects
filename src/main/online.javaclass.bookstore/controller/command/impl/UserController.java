package controller.command.impl;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import service.UserService;
import service.dto.UserDto;
import service.exception.AppException;

import java.io.PrintStream;
import java.util.List;

public class UserController {
    private final UserService userService;

    private static final Logger log = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void process(String request, PrintStream response) {
        if (request.startsWith("get all")) {
            getAll(response);
        } else if (request.startsWith("get ")) {
            get(request, response);
        } else if (request.startsWith("create ")) {
            create(request, response);
        } else if (request.startsWith("update ")) {
            update(request, response);
        } else if (request.startsWith("delete ")) {
            delete(request, response);
        } else {
            unknown(response);
        }
    }

    private static void unknown(PrintStream response) {
        response.println("Unknown command");
    }

    private void get(String request, PrintStream response) {
        try {
            Long id = Long.parseLong(request.split(" ")[1]);
            UserDto user = userService.find(id);
            response.println("User: ");
            response.println(user);
        } catch (AppException e) {
            response.println(e.getMessage());
        } catch (Exception e) {
            response.println("Sorry, smth went wrong ... SERVER ERROR");
        }
    }

    private void getAll(PrintStream response) {
        List<UserDto> users = userService.getAll();
        response.println("All users");
        for (UserDto user : users) {
            response.println(user);
        }
        response.println("End of list");
    }

    private void create(String request, PrintStream response) {
        try {
            String[] parts = request.split(" ", 6);
            if (parts.length < 6) {
                response.println("Invalid create command. Usage: create email password role firstName lastName");
                return;
            }
            String email = parts[1];
            String password = parts[2];
            String role = parts[3];
            String firstName = parts[4];
            String lastName = parts[5];

            UserDto userDto = new UserDto();
            userDto.setEmail(email);
            userDto.setPassword(password);
            userDto.setRole(role);
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            UserDto created = userService.create(userDto);

            response.println("User created:");
            response.println(created);

        } catch (AppException e) {
            response.println(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating user", e);
            response.println("Sorry, smth went wrong ... SERVER ERROR");
        }
    }

    private void update(String request, PrintStream response) {
        try {
            String[] parts = request.split(" ", 7);
            if (parts.length < 7) {
                response.println("Invalid update command. Usage: update id email password role firstName lastName");
                return;
            }
            Long id = Long.parseLong(parts[1]);
            String email = parts[2];
            String password = parts[3];
            String role = parts[4];
            String firstName = parts[5];
            String lastName = parts[6];

            UserDto userDto = new UserDto();
            userDto.setId(id);
            userDto.setEmail(email);
            userDto.setPassword(password);
            userDto.setRole(role);
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);

            UserDto updated = userService.update(userDto);
            response.println("User updated:");
            response.println(updated);
        } catch (AppException e) {
            response.println(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating user", e);
            response.println("Sorry, smth went wrong ... SERVER ERROR");
        }
    }

    private void delete(String request, PrintStream response) {
        try {
            String[] parts = request.split(" ");
            if (parts.length < 2) {
                response.println("Invalid delete command. Usage: delete id");
                return;
            }
            Long id = Long.parseLong(parts[1]);
            boolean deleted = userService.deleteById(id);
            if (deleted) {
                response.println("User deleted");
            } else {
                response.println("User not found");
            }
        } catch (AppException e) {
            response.println(e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting user", e);
            response.println("Sorry, smth went wrong ... SERVER ERROR");
        }
    }
}

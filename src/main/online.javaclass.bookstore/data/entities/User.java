package data.entities;


import lombok.Data;

@Data

public class User {
    private Long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}

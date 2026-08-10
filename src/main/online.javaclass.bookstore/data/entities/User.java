package data.entities;


import lombok.Data;

@Data

public class User {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;

}

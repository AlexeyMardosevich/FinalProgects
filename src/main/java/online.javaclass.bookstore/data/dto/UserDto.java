package online.javaclass.bookstore.data.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "should be not null")
    private String password;
    @Email
    @NotBlank(message = "should be not null")
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}

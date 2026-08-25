package online.javaclass.bookstore.data.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "should be not null")
    private String password;
    @Email
    @NotNull(message = "should be not null")
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}

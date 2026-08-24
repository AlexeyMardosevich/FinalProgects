package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@Log4j2
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    private String get(@PathVariable Long id, Model model) {
        UserDto userDto = userService.find(id);
        model.addAttribute("user", userDto);
        return "user";
    }

    @GetMapping("/getAll")
    private String getAll(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    private String createUserForm() {
        return "createUserForm";
    }

    @PostMapping("/update")
    private String createUSer(@ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users/" + userDto.getId();
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserDto userDto = userService.find(id);
        model.addAttribute("user", userDto);
        return "editUserForm";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users/" + userDto.getId();
    }

    @PostMapping("/delete/{id}")
    private String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users/getAll";
    }
}

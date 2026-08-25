package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import online.javaclass.bookstore.service.UserService;
import online.javaclass.bookstore.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserDto> userPage = userService.getAll(pageable);
        model.addAttribute("usersPage", userPage);
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "users";
    }

    @GetMapping("/create")
    private String createUserForm() {
        return "createUserForm";
    }

    @PostMapping("/create")
    private String createUSer(@ModelAttribute @Valid UserDto userDto) {
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

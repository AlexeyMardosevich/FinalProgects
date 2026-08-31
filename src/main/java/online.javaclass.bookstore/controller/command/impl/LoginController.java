package online.javaclass.bookstore.controller.command.impl;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false)
                            String logout, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid email or password");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out");
        }
        return "login";
    }
}

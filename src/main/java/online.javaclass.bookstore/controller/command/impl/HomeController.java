package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
@RequiredArgsConstructor
public class HomeController {
    private final MessageSource messageSource;

    @GetMapping
    private String home(Model model){
        return "index";
    }
}

package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping ("/")
@RequiredArgsConstructor
public class HomeController {
    private final MessageSource messageSource;
    private final OrderService orderService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Long userId = getCurrentUserId(session);
        if (userId != null) {
            OrderDto cart = orderService.getCart(userId);
            model.addAttribute("cart", cart);
        }
        return "index";
    }

    private Long getCurrentUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId instanceof Long) {
            return (Long) userId;
        }
        return null;
    }
}
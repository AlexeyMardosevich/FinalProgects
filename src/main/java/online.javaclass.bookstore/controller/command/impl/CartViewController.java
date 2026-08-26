package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartViewController {

    private final OrderService orderService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return "redirect:/login";
        }
        OrderDto cart = orderService.getCart(userId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/items")
    public String addToCart(@RequestParam Long bookId, @RequestParam(defaultValue = "1") Integer quantity, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return "redirect:/login";
        }
        orderService.addToCart(userId, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/items/{bookId}")
    public String changeQuantity(@PathVariable Long bookId, @RequestParam Integer quantity, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return "redirect:/login";
        }
        orderService.changeCartItem(userId, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/items/{bookId}/remove")
    public String removeFromCart(@PathVariable Long bookId, HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return "redirect:/login";
        }
        orderService.removeFromCart(userId, bookId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        Long userId = getCurrentUserId(session);
        if (userId == null) {
            return "redirect:/login";
        }
        OrderDto order = orderService.checkout(userId);
        return "redirect:/orders/" + order.getId();
    }

    private Long getCurrentUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId instanceof Long) {
            return (Long) userId;
        }
        return null;
    }
}

package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;

    @GetMapping("/cart")
    public OrderDto getCart(@RequestParam Long userId) {
        return orderService.getCart(userId);
    }

    @PostMapping("/cart/items")
    public OrderDto addToCart(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam Integer quantity) {
        return orderService.addToCart(userId, bookId, quantity);
    }

    @PatchMapping("/cart/items/{bookId}")
    public OrderDto changeCartItem(@RequestParam Long userId, @PathVariable Long bookId, @RequestParam Integer quantity) {
        return orderService.changeCartItem(userId, bookId, quantity);
    }

    @DeleteMapping("/cart/items/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromCart(@RequestParam Long userId, @PathVariable Long bookId) {
        orderService.removeFromCart(userId, bookId);
    }

    @PostMapping("/cart/checkout")
    public OrderDto checkout(@RequestParam Long userId) {
        return orderService.checkout(userId);
    }
}

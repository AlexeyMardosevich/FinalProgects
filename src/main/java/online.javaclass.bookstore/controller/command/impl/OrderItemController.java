package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.service.OrderItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{orderId}/items")
    public String getItems(@PathVariable Long orderId, Model model) {
        model.addAttribute("items" , orderItemService.findAllByOrderId(orderId));
        model.addAttribute("orderId" , orderId);
        return "orderItems";
    }

    @GetMapping("/items/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        model.addAttribute("item" , orderItemService.find(id));
        return "orderItem";
    }

    @PostMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return "redirect:/orders";
    }
}

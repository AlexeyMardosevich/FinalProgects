package online.javaclass.bookstore.controller.command.impl;

import lombok.RequiredArgsConstructor;
import online.javaclass.bookstore.data.dto.PageResponseDto;
import online.javaclass.bookstore.data.dto.PageableDto;
import online.javaclass.bookstore.service.OrderService;
import online.javaclass.bookstore.service.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("order" , orderService.find(id));
        return "order";
    }

    @GetMapping("/getAll")
    public String getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model) {

        PageableDto pageableDto = new PageableDto(page, pageSize);
        PageResponseDto<OrderDto> response =
                orderService.getAll(pageableDto);
        model.addAttribute("orders" , response.getItems());
        model.addAttribute("page" , response.getPage());
        model.addAttribute("pageSize" , response.getPageSize());
        model.addAttribute("totalItems" , response.getTotalItems());
        model.addAttribute("totalPages" , response.getTotalPages());
        return "orders";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("order" , new OrderDto());
        return "createOrderForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("order") OrderDto orderDto) {
        OrderDto created = orderService.create(orderDto);
        return "redirect:/orders/" + created.getId();
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("order" , orderService.find(id));
        return "editOrderForm";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("order") OrderDto orderDto) {
        orderDto.setId(id);
        orderService.update(orderDto);
        return "redirect:/orders/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders/getAll";
    }
}

package pl.wszib.pizzamarket.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wszib.pizzamarket.data.entities.OrderEntity;
import pl.wszib.pizzamarket.data.repositories.OrderRepository;
import pl.wszib.pizzamarket.services.OrderService;
import pl.wszib.pizzamarket.web.models.OrderAddressModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("admin/orders")
public class OrdersController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrdersController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrder(Model model) {
        List<OrderEntity> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orderPage";
    }

    @GetMapping("order/{orderId}")
    public String confirmOrder(
            @PathVariable Long orderId,
            @ModelAttribute("orderAddress") OrderAddressModel orderAddress,
            Model model) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("order", order);
        return "orderPage";
    }
}

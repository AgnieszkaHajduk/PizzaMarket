package pl.wszib.pizzamarket.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wszib.pizzamarket.data.entities.OrderEntity;
import pl.wszib.pizzamarket.data.repositories.OrderRepository;
import pl.wszib.pizzamarket.services.OrderService;
import pl.wszib.pizzamarket.web.models.OrderAddressModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("confirmation")
public class ConfirmationController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public ConfirmationController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String showOrder(Model model) {
        List<OrderEntity> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orderPizzaConfirmed";
    }
}

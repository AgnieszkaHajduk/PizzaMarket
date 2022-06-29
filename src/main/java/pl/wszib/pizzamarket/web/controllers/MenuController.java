package pl.wszib.pizzamarket.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wszib.pizzamarket.data.entities.PizzaEntity;
import pl.wszib.pizzamarket.data.repositories.PizzaRepository;
import pl.wszib.pizzamarket.services.OrderService;
import pl.wszib.pizzamarket.web.models.OrderAddressModel;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {

    private final PizzaRepository pizzaRepository;
    private final OrderService orderService;

    public MenuController(PizzaRepository pizzaRepository, OrderService orderService) {
        this.pizzaRepository = pizzaRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public String showPizzaMenu(Model model) {
        List<PizzaEntity> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "menuPage";
    }

    @GetMapping("order/{pizzaId}")
    public String showPizzaOrderPage(
            @PathVariable Long pizzaId,
            @ModelAttribute("orderAddress") OrderAddressModel orderAddress,
            Model model) {
        PizzaEntity pizza = pizzaRepository.findById(pizzaId).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("pizza", pizza);
        return "orderPizzaPage";
    }

    @PostMapping("order/{pizzaId}")
    public String processPizzaOrder(
            @PathVariable Long pizzaId,
            @ModelAttribute("orderAddress") @Valid OrderAddressModel orderAddress,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            PizzaEntity pizza = pizzaRepository.findById(pizzaId).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("pizza", pizza);

            return "orderPizzaPage";
        }

        orderService.saveOrder(pizzaId, orderAddress);

        return "redirect:/confirmation";
    }
}

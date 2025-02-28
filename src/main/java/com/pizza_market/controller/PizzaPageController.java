package com.pizza_market.controller;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PizzaPageController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);
        return "index";  // имя шаблона
    }

    @GetMapping("/pizza")
    public String pizza(@RequestParam(name="pizzaId") Long pizzaId, Model model) {
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        model.addAttribute("pizza", pizza);
        return "pizza";  // имя шаблона
    }

    @GetMapping("/order")
    public String order(@RequestParam(name="pizzaId") Long pizzaId, Model model) {
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        pizzaService.addOrder(pizza);
        String response = String.format("Пицца \"%s\" заказана!", pizza.getPizzaName());
        model.addAttribute("message", response);
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);
        return "index";
    }
}
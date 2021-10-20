package com.pizza_market.controller;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.services.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexPageController {
    @GetMapping("/")
    public String index(Model model) {
        List<Pizza> pizzas = PizzaService.getAllPizza();
        model.addAttribute("pizzas", pizzas);
        return "index";  // имя шаблона
    }

    @GetMapping("/pizza")
    public String pizza(@RequestParam(name="pizzaId", required=true) Integer pizzaId, Model model) {
        Pizza pizza = PizzaService.getPizzaById(pizzaId);
        model.addAttribute("pizza", pizza);
        return "index";  // имя шаблона
    }
}
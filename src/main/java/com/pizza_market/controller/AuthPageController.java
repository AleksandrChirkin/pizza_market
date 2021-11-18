package com.pizza_market.controller;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.Role;
import com.pizza_market.db.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;


@Controller
public class AuthPageController {
    @Autowired
    private PizzaService service;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";  // имя шаблона
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("client", new Client());
        return "signup";  // имя шаблона
    }

    @PostMapping("/signup")
    public String addClient(Client client, Map<String, Object> model) {
        String clientMail = client.getEmail();
        if (clientMail == null) {
            model.put("message", "No email written!");
            return "signup";
        }
        Client clientFromDb = service.findClientByEmail(clientMail);
        if (clientFromDb != null) {
            model.put("message", "User exists!");
            return "signup";
        }
        client.setId((long) clientMail.hashCode());
        client.setLastDate(new Date(Instant.now().toEpochMilli()));
        client.setActive(true);
        if (clientMail.equals("admin@admin.admin"))
            client.setRoles(Collections.singleton(Role.ADMIN));
        else
            client.setRoles(Collections.singleton(Role.USER));
        service.saveClient(client);
        model.put("message", "Пользоавтель добавлен!");
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return getUser(model);  // имя шаблона
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("pizzas", service.getAllPizza());
        return "admin";
    }

    @GetMapping("/addPizza")
    public String addPizzaPage(Model model){
        model.addAttribute("pizza", new Pizza());
        return "addPizza";
    }

    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, Map<String, Object> model){
        service.addPizza(pizza);
        model.put("message", String.format("Пицца %s добавлена", pizza.getPizzaName()));
        model.put("pizzas", service.getAllPizza());
        return "admin";
    }

    @GetMapping("/removePizza")
    public String removePizza(@RequestParam(name="pizzaId") Long pizzaId, Model model){
        String pizzaName = service.getPizzaById(pizzaId).getPizzaName();
        service.removePizzaById(pizzaId);
        model.addAttribute("message", String.format("Пицца %s успешно удалена!", pizzaName));
        model.addAttribute("pizzas", service.getAllPizza());
        return "admin";
    }

    @GetMapping("/removeOrder")
    public String removeOrder(@RequestParam(name="orderId") Long orderId, Model model){
        service.removeOrderById(orderId);
        return getUser(model);
    }

    private String getUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = service.findClientByEmail(auth.getName());
        model.addAttribute("userName", String.format("%s %s", client.getFirstName(), client.getLastName()));
        model.addAttribute("orders", service.getOrdersByUserId(client.getId()));
        return "user";
    }
}

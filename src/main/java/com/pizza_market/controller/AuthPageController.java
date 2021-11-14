package com.pizza_market.controller;

import com.pizza_market.db.dao.*;
import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ClientDAO clientDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private PizzaDAO pizzaDAO;

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
        Client clientFromDb = clientDAO.findByEmail(clientMail);
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
        clientDAO.save(client);
        model.put("message", "Пользоавтель добавлен!");
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";  // имя шаблона
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("pizzas", pizzaDAO.getAllPizzas());
        return "admin";
    }

    @GetMapping("/addPizza")
    public String addPizzaPage(Model model){
        model.addAttribute("pizza", new Pizza());
        return "addPizza";
    }

    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, Map<String, Object> model){
        pizzaDAO.addPizza(pizza);
        model.put("message", String.format("Пицца %s добавлена", pizza.getPizzaName()));
        model.put("pizzas", pizzaDAO.getAllPizzas());
        return "admin";
    }

    @GetMapping("/removePizza")
    public String removePizza(@RequestParam(name="pizzaId") Long pizzaId, Model model){
        String pizzaName = pizzaDAO.getPizzaById(pizzaId).getPizzaName();
        pizzaDAO.removePizzaById(pizzaId);
        model.addAttribute("message", String.format("Пицца %s успешно удалена!", pizzaName));
        model.addAttribute("pizzas", pizzaDAO.getAllPizzas());
        return "admin";
    }
}

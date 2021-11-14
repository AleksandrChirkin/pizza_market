package com.pizza_market.controller;

import com.pizza_market.db.dao.ClientDAO;
import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;


@Controller
public class AuthPageController {
    @Autowired
    private ClientDAO clientDAO;

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
        if (clientMail == null)
            return "signup";
        Client clientFromDb = clientDAO.findByEmail(clientMail);
        if (clientFromDb != null) {
            model.put("message", "User exists!");
            return "signup";
        }
        client.setId((long) clientMail.hashCode());
        client.setLastDate(new Date(Instant.now().toEpochMilli()));
        client.setActive(true);
        client.setRoles(Collections.singleton(Role.USER));
        clientDAO.save(client);

        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";  // имя шаблона
    }
}

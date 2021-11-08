package com.pizza_market.controller;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class AuthPageController {
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";  // имя шаблона
    }

    @PostMapping("/login")
    public String login(Model model) {
        System.out.println(model);
        return "";
    }

    @PostMapping("/signup")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = UserDAO.findByEmail(user.getEmail());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "signup";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton());
        UserDAO.save(user);

        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "user";  // имя шаблона
    }
}

package com.pizza_market.db.services;

import com.pizza_market.db.dao.PizzaDAO;
import com.pizza_market.db.entities.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaDAO pizzaDAO;

    public List<Pizza> getAllPizza() {
        return pizzaDAO.getAllPizza();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaDAO.getPizzaById(id);
    }
}

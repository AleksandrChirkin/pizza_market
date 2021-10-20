package com.pizza_market.db.services;

import com.pizza_market.db.dao.PizzaDAO;
import com.pizza_market.db.entities.Pizza;

import java.util.List;

public class PizzaService {

    private static PizzaDAO pizzaDAO = new PizzaDAO();

    public static List<Pizza> getAllPizza() {
        return pizzaDAO.getAllPizza();
    }

    public static Pizza getPizzaById(Integer id) {
        return pizzaDAO.getPizzaById(id);
    }
}

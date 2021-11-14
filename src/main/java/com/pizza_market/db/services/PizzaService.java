package com.pizza_market.db.services;

import com.pizza_market.db.dao.ClientDAO;
import com.pizza_market.db.dao.OrderDAO;
import com.pizza_market.db.dao.PizzaDAO;
import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.PizzaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private PizzaDAO pizzaDAO;

    @Autowired
    private OrderDAO orderDAO;

    public List<Pizza> getAllPizza() {
        return pizzaDAO.getAllPizza();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaDAO.getPizzaById(id);
    }

    public void addOrder(Pizza pizza){
        PizzaOrder order = new PizzaOrder();
        order.setDate(new Date(Instant.now().toEpochMilli()));
        order.setPizza(pizza);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientDAO.findByEmail(auth.getName());
        order.setClient(client);
        order.setLatitude(0.0f);
        order.setLongitude(0.0f);
        orderDAO.addOrder(order);
    }
}

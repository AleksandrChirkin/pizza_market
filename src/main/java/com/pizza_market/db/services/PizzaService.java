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

    public List<Pizza> getAllPizzas() {
        return pizzaDAO.getAllPizzas();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaDAO.getPizzaById(id);
    }

    public void addPizza(Pizza pizza){
        pizzaDAO.addPizza(pizza);
    }

    public void removePizzaById(Long id){
        pizzaDAO.removePizzaById(id);
    }

    public List<PizzaOrder> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    public List<PizzaOrder> getOrdersByUserId(Long id){
        return orderDAO.getOrdersByUserId(id);
    }

    public void addOrder(Pizza pizza){
        PizzaOrder order = new PizzaOrder();
        order.setDate(new Date(Instant.now().toEpochMilli()));
        order.setPizza(pizza);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientDAO.getClientByEmail(auth.getName());
        order.setClient(client);
        orderDAO.addOrder(order);
    }

    public void removeOrderById(Long id){
        orderDAO.removeOrderById(id);
    }

    public List<Client> getAllClients(){
        return clientDAO.getAllClients();
    }

    public Client getClientByEmail(String email){
        return clientDAO.getClientByEmail(email);
    }

    public Client getClientById(Long id){
        return clientDAO.getClientById(id);
    }

    public void saveClient(Client client){
        clientDAO.save(client);
    }

    public void removeClientById(Long id){
        clientDAO.removeClient(id);
    }
}

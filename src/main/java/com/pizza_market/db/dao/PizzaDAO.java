package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// похорошему здесь нужно было написать интерфейс но мне лень
@Component
public class PizzaDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Autowired
    private OrderDAO orderDAO;

    public void addPizza(Pizza pizza) {
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.save(pizza);
        transaction.commit();
        session.close();
    }

    public List<Pizza> getAllPizzas() {
        return hibernateUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Pizza", Pizza.class)
                .list();
    }

    public Pizza getPizzaById(Long id) {
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        Pizza pizza = session.get(Pizza.class, id);
        session.close();
        return pizza;
    }

    public void removePizzaById(Long id){
        orderDAO.removeAllOrdersByPizzaId(id);
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getPizzaById(id));
        transaction.commit();
        session.close();
    }
}

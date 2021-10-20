package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// похорошему здесь нужно было написать интерфейс но мне лень
public class PizzaDAO {

    public List<Pizza> getAllPizza() {

        return (List<Pizza>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Pizza")
                .list();
    }

    public Pizza getPizzaById(Integer id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Pizza.class, id);
    }
}

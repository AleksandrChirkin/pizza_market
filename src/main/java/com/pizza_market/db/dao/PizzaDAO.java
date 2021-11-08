package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.springframework.stereotype.Component;

import java.util.List;

// похорошему здесь нужно было написать интерфейс но мне лень
@Component
public class PizzaDAO {
    // пока экземпляр пиццы создаем так, но это нужно убрать, когда запилим админку
    // TODO: избавиться от Native SQL, поработать с сущностями

    public List<Pizza> getAllPizza() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Pizza", Pizza.class)
                .list();
    }

    public Pizza getPizzaById(Long id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Pizza.class, id);
    }
}

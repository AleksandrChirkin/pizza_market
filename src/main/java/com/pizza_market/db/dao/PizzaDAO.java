package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

// похорошему здесь нужно было написать интерфейс но мне лень
@Component
public class PizzaDAO {
    /*// пока экземпляр пиццы создаем так, но это нужно убрать, когда запилим админку
    private PizzaDAO(){
        if (getAllPizza().isEmpty()) {
            Pizza pizza = new Pizza();
            pizza.setPizzaName("Маргарита");
            save(pizza);
        }
    }

    public void save(Pizza pizza){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(pizza);
        tx1.commit();
        session.close();
    }*/

    public List<Pizza> getAllPizza() {

        return (List<Pizza>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from Pizza")
                .list();
    }

    public Pizza getPizzaById(Long id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Pizza.class, id);
    }
}

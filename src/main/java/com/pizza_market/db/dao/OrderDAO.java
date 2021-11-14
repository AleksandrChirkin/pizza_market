package com.pizza_market.db.dao;

import com.pizza_market.db.entities.PizzaOrder;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {
    public void addOrder(PizzaOrder order){
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    public List<PizzaOrder> getAllOrders(){
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory().openSession();
        List<PizzaOrder> orders = session
                .createQuery("from PizzaOrder", PizzaOrder.class)
                .list();
        session.close();
        return orders;
    }

    public void removeAllOrdersByPizzaId(Long pizzaId){
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        for (PizzaOrder order: getAllOrders()) {
            if (order.getPizza().getId().equals(pizzaId)) {
                Transaction transaction = session.beginTransaction();
                session.delete(order);
                transaction.commit();
            }
        }
        session.close();
    }
}

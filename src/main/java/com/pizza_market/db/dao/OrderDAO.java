package com.pizza_market.db.dao;

import com.pizza_market.db.entities.PizzaOrder;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

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
}

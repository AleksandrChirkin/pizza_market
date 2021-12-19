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
                .getSessionFactory()
                .openSession();
        List<PizzaOrder> orders = session
                .createQuery("from PizzaOrder", PizzaOrder.class)
                .list();
        session.close();
        return orders;
    }

    public List<PizzaOrder> getOrdersByUserId(Long id){
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createNativeQuery(
                        (String.format("SELECT * FROM PIZZA_ORDER p_order WHERE p_order.client_id = %d", id)), PizzaOrder.class)
                .list();
    }

    public void removeOrderById(Long id){
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PizzaOrder orderToRemove = session.get(PizzaOrder.class, id);
        Transaction transaction = session.beginTransaction();
        session.delete(orderToRemove);
        transaction.commit();
        session.close();
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

    public void removeAllOrdersByClientId(Long clientId){
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        for (PizzaOrder order: getAllOrders()) {
            if (order.getClient().getId().equals(clientId)) {
                Transaction transaction = session.beginTransaction();
                session.delete(order);
                transaction.commit();
            }
        }
        session.close();
    }
}

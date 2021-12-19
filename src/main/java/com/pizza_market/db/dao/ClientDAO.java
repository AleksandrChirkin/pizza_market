package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.PizzaOrder;
import com.pizza_market.db.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ClientDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Autowired
    private OrderDAO orderDAO;

    public List<Client> getAllClients(){
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        List<Client> orders = session
                .createQuery("from Client", Client.class)
                .list();
        session.close();
        return orders;
    }

    public Client getClientById(Long id){
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public Client getClientByEmail(String email) {
        return hibernateUtil
                .getSessionFactory()
                .openSession().get(Client.class, (long) email.hashCode());
    }

    public void save(Client client){
        Session session = hibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public void removeClient(Long id){
        orderDAO.removeAllOrdersByClientId(id);
        Session session = hibernateUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getClientById(id));
        transaction.commit();
        session.close();
    }
}

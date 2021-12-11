package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClientDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

    public Client findByEmail(String email) {
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
}

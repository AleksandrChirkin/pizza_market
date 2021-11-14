package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;


@Component
public class ClientDAO {
    public Client findByEmail(String email) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession().get(Client.class, (long) email.hashCode());
    }

    public void save(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }
}

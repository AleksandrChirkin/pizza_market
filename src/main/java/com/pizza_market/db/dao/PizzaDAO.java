package com.pizza_market.db.dao;

import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

// похорошему здесь нужно было написать интерфейс но мне лень
@Component
public class PizzaDAO {
    // пока экземпляр пиццы создаем так, но это нужно убрать, когда запилим админку
    // TODO: избавиться от Native SQL, поработать с сущностями
    private PizzaDAO(){
        if (getAllPizza().isEmpty()) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Query query1 = session.createSQLQuery(
                    "insert into PIZZA (pizza_name) values ('Маргарита');"
            );
            query1.executeUpdate();
            Query query2 = session.createSQLQuery(
                    "insert into PIZZA (pizza_name, description) values ('Гавайская', 'Имеются ананасы')"
            );
            query2.executeUpdate();
            tx1.commit();
            session.close();
        }
    }

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

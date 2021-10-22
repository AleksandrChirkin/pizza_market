package com.pizza_market.db.utils;

//import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Ingredient;
import com.pizza_market.db.entities.Pizza;
//import com.pizza_market.db.entities.PizzaOrder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                //configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Ingredient.class);
                configuration.addAnnotatedClass(Pizza.class);
                //configuration.addAnnotatedClass(PizzaOrder.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}

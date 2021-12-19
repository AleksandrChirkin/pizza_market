package com.pizza_market.db.utils;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.PizzaOrder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.url",
                        System.getenv("SPRING_DATASOURCE_URL"));
                configuration.setProperty("hibernate.connection.username",
                        System.getenv("SPRING_DATASOURCE_USERNAME"));
                configuration.setProperty("hibernate.connection.password",
                        System.getenv("SPRING_DATASOURCE_PASSWORD"));
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Pizza.class);
                configuration.addAnnotatedClass(PizzaOrder.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(getServiceRegistry())
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    private static BootstrapServiceRegistry getServiceRegistry(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return new BootstrapServiceRegistryBuilder().applyClassLoader(loader).build();
    }
}


package com.pizza_market.db.utils;

import com.pizza_market.db.entities.Client;
import com.pizza_market.db.entities.Pizza;
import com.pizza_market.db.entities.PizzaOrder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class HibernateUtil {
    private SessionFactory sessionFactory;
    private DataSource source;

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.url", System.getenv("DATABASE_URL"));
            configuration.setProperty("hibernate.connection.username", System.getenv("DATABASE_USER"));
            configuration.setProperty("hibernate.connection.password", System.getenv("DATABASE_PASSWORD"));
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Pizza.class);
            configuration.addAnnotatedClass(PizzaOrder.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(getServiceRegistry())
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            source = new DriverManagerDataSource(System.getenv("DATABASE_URL"),
                    System.getenv("DATABASE_USER"),
                    System.getenv("DATABASE_PASSWORD"));
        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public DataSource getSource(){
        return source;
    }

    private static BootstrapServiceRegistry getServiceRegistry(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return new BootstrapServiceRegistryBuilder().applyClassLoader(loader).build();
    }
}

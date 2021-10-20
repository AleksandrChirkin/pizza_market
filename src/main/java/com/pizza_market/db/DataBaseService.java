package com.pizza_market.db;

import com.pizza_market.db.entities.Pizza;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Service
public class DataBaseService {
    private static final EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("pizza_market");
    public static final EntityManager manager = factory.createEntityManager();

    private DataBaseService(){}

    public static List<Pizza> getAllPizzas() {
        return DataBaseService.manager.createQuery("SELECT * FROM pizza", Pizza.class).getResultList();
    }
}

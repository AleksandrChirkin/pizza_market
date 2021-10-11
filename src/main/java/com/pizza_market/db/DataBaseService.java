package com.pizza_market.db;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class DataBaseService {
    private final EntityManager manager;

    private DataBaseService(){
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("pizza_market");
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
    }
}

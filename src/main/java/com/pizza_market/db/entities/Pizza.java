package com.pizza_market.db.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="PIZZA")
public class Pizza {
    @Id
    private long id;

    @ManyToMany
    private Set<Ingredient> ingredients;

    @Column(name = "pizza_name", nullable = false, length = 50)
    private String pizzaName;

    @Column
    private String description;
}

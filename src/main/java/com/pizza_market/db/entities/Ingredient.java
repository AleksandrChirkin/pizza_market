package com.pizza_market.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="INGREDIENT")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Pizza> pizzas;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}

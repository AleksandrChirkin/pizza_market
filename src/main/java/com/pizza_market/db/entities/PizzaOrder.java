package com.pizza_market.db.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="PIZZA_ORDER")
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private Date date;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Pizza pizza;

    @Column(name = "lat", nullable = false)
    private Float latitude;

    @Column(name = "long", nullable = false)
    private Float longitude;
}

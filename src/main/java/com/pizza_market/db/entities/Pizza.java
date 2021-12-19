package com.pizza_market.db.entities;

import javax.persistence.*;

@Entity
@Table(name="PIZZA")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pizza_name", nullable = false, length = 50)
    private String pizzaName;

    @Column
    private String description;

    @Column
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName){
        this.pizzaName = pizzaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice(){
        return price;
    }

    public void setPrice(Float price){
        this.price = price;
    }
}

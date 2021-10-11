package com.pizza_market.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="CLIENT")
public class Client {
    @Id
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "tel", length = 12)
    private String telephone;

    @Column
    private String email;

    @Column(name = "last_date", nullable = false)
    private Date lastDate;
}

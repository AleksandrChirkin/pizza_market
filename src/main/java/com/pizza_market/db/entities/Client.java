package com.pizza_market.db.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;

    @Column(name = "tel", length = 12)
    private String telephone;

    @Column
    private String email;

    @Column(name = "last_date", nullable = true)
    private Date lastDate;

    @Column(name="is_active")
    private Boolean isActive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name="client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

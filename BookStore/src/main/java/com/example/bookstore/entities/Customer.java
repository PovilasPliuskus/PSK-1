package com.example.bookstore.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
@Getter @Setter
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FirstName")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "LastName")
    @Basic(optional = false)
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private List<CustomOrder> customOrders = new ArrayList<>();
}

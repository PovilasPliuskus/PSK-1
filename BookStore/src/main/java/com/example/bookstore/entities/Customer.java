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
    private Integer Id;

    @Column(name = "FirstName")
    @Basic(optional = false)
    private String FirstName;

    @Column(name = "MiddleName")
    private String MiddleName;

    @Column(name = "LastName")
    @Basic(optional = false)
    private String LastName;

    @OneToMany(mappedBy = "Customer")
    private List<CustomOrder> CustomOrders = new ArrayList<>();
}

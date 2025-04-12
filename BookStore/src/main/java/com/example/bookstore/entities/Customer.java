package com.example.bookstore.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
@Getter @Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select a from Customer as a")
})
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

    public Customer(String firstName, String middleName, String lastName)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String middleName, String lastName, List<CustomOrder> customOrders)
    {
        this(firstName, middleName, lastName);
        this.customOrders = customOrders;
    }
}

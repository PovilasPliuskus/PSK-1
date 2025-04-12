package com.example.bookstore.entities;

import javax.persistence.*;

import com.example.bookstore.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CustomOrder")
@Getter @Setter
public class CustomOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PaymentMethod")
    @Basic(optional = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name="FkCustomerId")
    private Customer customer;

    @ManyToMany(mappedBy = "customOrders")
    List<Book> books = new ArrayList<>();
}

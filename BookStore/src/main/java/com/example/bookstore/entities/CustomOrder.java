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
    private Integer Id;

    @Column(name = "PaymentMethod")
    @Basic(optional = false)
    private PaymentMethod PaymentMethod;

    @ManyToOne
    @JoinColumn(name="FkCustomerId")
    private Customer Customer;

    @ManyToMany(mappedBy = "CustomOrders")
    List<Book> Books = new ArrayList<>();
}

package com.example.bookstore.entities;

import javax.persistence.*;

import com.example.bookstore.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Order")
@Getter @Setter
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "PaymentMethod")
    @Basic(optional = false)
    private PaymentMethod PaymentMethod;
}

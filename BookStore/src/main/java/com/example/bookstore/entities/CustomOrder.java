package com.example.bookstore.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CustomOrder")
@Getter @Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CustomOrder.findAll", query = "select a from CustomOrder as a")
})
public class CustomOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PaymentMethod")
    @Basic(optional = false)
    private String string;

    @ManyToOne
    @JoinColumn(name="FkCustomerId")
    private Customer customer;

    @ManyToMany(mappedBy = "customOrders")
    List<Book> books = new ArrayList<>();

    public CustomOrder(String string)
    {
        this.string = string;
    }

    public CustomOrder(String string, Customer customer)
    {
        this(string);
        this.customer = customer;
    }
}

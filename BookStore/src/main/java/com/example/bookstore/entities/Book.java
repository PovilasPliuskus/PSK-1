package com.example.bookstore.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter @Setter
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title")
    @Basic(optional = false)
    private String title;

    @Column(name = "Author")
    @Basic(optional = false)
    private String author;

    @Column(name = "PageCount")
    @Basic(optional = false)
    private Integer pageCount;

    @Column(name = "Price")
    @Basic(optional = false)
    private Float price;

    @Column
    @ManyToMany
    @JoinTable(
            name = "BookOrder",
            joinColumns = @JoinColumn(name = "FkBookId"),
            inverseJoinColumns = @JoinColumn(name = "FkOrderId")
    )
    private List<CustomOrder> customOrders = new ArrayList<>();
}

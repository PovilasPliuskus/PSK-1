package com.example.bookstore.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter @Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select a from Book as a"),
        @NamedQuery(name = "Book.findByTitle", query = "select a from Book as a where lower(a.title) = lower(:title)")
})
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

    public Book(String title, String author, Integer pageCount, Float price)
    {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.price = price;
    }
}

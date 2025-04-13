package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.mybatis.model.Book;
import com.example.bookstore.mybatis.model.Customer;
import com.example.bookstore.mybatis.model.Customorder;
import com.example.bookstore.persistence.mybatis.BooksMyBatisDAO;
import com.example.bookstore.persistence.mybatis.CustomOrdersMyBatisDAO;
import com.example.bookstore.persistence.mybatis.CustomersMyBatisDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CustomOrdersForCustomerMyBatis implements Serializable {

    @Inject
    private CustomOrdersMyBatisDAO customOrdersMyBatisDAO;

    @Inject
    private CustomersMyBatisDAO customersMyBatisDAO;

    @Inject
    private BooksMyBatisDAO booksMyBatisDAO;

    @Getter
    @Setter
    private Customorder customOrder;

    @Getter
    @Setter
    private Customer customer;

    @Getter
    @Setter
    private Customer customerToCreate = new Customer();

    @Getter
    @Setter
    private Customorder customOrderToCreate = new Customorder();

    @Getter
    @Setter
    private Book bookToAdd = new Book();

    @Getter
    @Setter
    private List<SelectItem> allBooks;

    @Getter
    @Setter
    private List<SelectItem> allCustomOrders;

    @Transactional
    public String createCustomOrder() {
        customOrderToCreate.setCustomer(this.customer);
        customOrdersMyBatisDAO.persist(customOrderToCreate);
        return "/myBatis/customOrders?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @Transactional
    public String createCustomOrderAndBook() {
        customOrderToCreate.setCustomer(this.customer);

        if (customOrderToCreate.getBooks() == null) {
            customOrderToCreate.setBooks(new ArrayList<>());
        }

        if (bookToAdd.getCustomorders() == null) {
            bookToAdd.setCustomorders(new ArrayList<>());
        }

        bookToAdd.getCustomorders().add(customOrderToCreate);
        customOrderToCreate.getBooks().add(bookToAdd);

        customOrdersMyBatisDAO.persist(customOrderToCreate);
        booksMyBatisDAO.persist(bookToAdd);

        return "/myBatis/customOrders?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int customerId = Integer.parseInt(requestParams.get("customerId"));
        this.customer = customersMyBatisDAO.findOne(customerId);
        this.allBooks = new ArrayList<>();
        List<Book> availableBooks = booksMyBatisDAO.findAll();
        for (Book book : availableBooks) {
            this.allBooks.add(new SelectItem(book, book.getTitle()));
        }
        customOrderToCreate = new Customorder();
        bookToAdd = new Book();
    }
}

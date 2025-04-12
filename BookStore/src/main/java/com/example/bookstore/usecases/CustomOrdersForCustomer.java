package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CustomOrder;
import com.example.bookstore.entities.Customer;
import com.example.bookstore.persistence.BooksDAO;
import com.example.bookstore.persistence.CustomOrdersDAO;
import com.example.bookstore.persistence.CustomersDAO;
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
public class CustomOrdersForCustomer implements Serializable {

    @Inject
    private CustomOrdersDAO customOrdersDAO;

    @Inject
    private CustomersDAO customersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter
    @Setter
    private CustomOrder customOrder;

    @Getter
    @Setter
    private Customer customer;

    @Getter
    @Setter
    private Customer customerToCreate = new Customer();

    @Getter
    @Setter
    private CustomOrder customOrderToCreate = new CustomOrder();

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
        customOrdersDAO.persist(customOrderToCreate);
        return "customOrders?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @Transactional
    public String createCustomOrderAndBook() {
        customOrderToCreate.setCustomer(this.customer);

        if (customOrderToCreate.getBooks() == null) {
            customOrderToCreate.setBooks(new ArrayList<>());
        }

        if (bookToAdd.getCustomOrders() == null) {
            bookToAdd.setCustomOrders(new ArrayList<>());
        }

        bookToAdd.getCustomOrders().add(customOrderToCreate);
        customOrderToCreate.getBooks().add(bookToAdd);

        customOrdersDAO.persist(customOrderToCreate);
        booksDAO.persist(bookToAdd);

        return "customOrders?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int customerId = Integer.parseInt(requestParams.get("customerId"));
        this.customer = customersDAO.findOne(customerId);
        this.allBooks = new ArrayList<>();
        List<Book> availableBooks = booksDAO.findAll();
        for (Book book : availableBooks) {
            this.allBooks.add(new SelectItem(book, book.getTitle()));
        }
        customOrderToCreate = new CustomOrder();
        bookToAdd = new Book();
    }

}

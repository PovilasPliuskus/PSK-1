package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CustomOrder;
import com.example.bookstore.persistence.BooksDAO;
import com.example.bookstore.persistence.CustomOrdersDAO;
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

import static java.util.Objects.isNull;
import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class BooksForCustomOrder implements Serializable {

    @Inject
    private CustomOrdersDAO customOrdersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter
    @Setter
    private CustomOrder customOrder;

    @Getter
    @Setter
    private Book bookToAdd = new Book();

    @Getter
    @Setter
    private List<SelectItem> allBooks;

    @Transactional
    public String createBookFromDropdown() {
        List<CustomOrder> allBookCustomOrders = bookToAdd.getCustomOrders();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomOrders(allBookCustomOrders);
        booksDAO.update(bookToAdd);
        return "books?faces-redirect=true&customOrderId=" + this.customOrder.getId();
    }

    @Transactional
    public String createBook() {
        Book foundBook = booksDAO.findByTitle(bookToAdd.getTitle());
        if (isNull(foundBook)) {
            handleNewBook();
        }
        else {
            handleExistingBook(foundBook);
        }

        return "books?faces-redirect=true&customOrderId=" + this.customOrder.getId();
    }

    private void handleExistingBook(Book foundBook) {
        bookToAdd.setId(foundBook.getId());
        List<CustomOrder> allBookCustomOrders = foundBook.getCustomOrders();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomOrders(allBookCustomOrders);
        booksDAO.update(bookToAdd);
    }

    private void handleNewBook() {
        List<CustomOrder> allBookCustomOrders = new ArrayList<>();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomOrders(allBookCustomOrders);
        booksDAO.persist(bookToAdd);
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int customOrderId = Integer.parseInt(requestParams.get("customOrderId"));
        this.customOrder = customOrdersDAO.findOne(customOrderId);
        this.allBooks = new ArrayList<>();
        List<Book> availableBooks = booksDAO.findAll();
        for (Book book : availableBooks) {
            this.allBooks.add(new SelectItem(book, book.getTitle()));
        }
    }
}

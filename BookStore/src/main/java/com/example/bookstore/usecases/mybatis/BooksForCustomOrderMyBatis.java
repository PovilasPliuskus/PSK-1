package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.mybatis.model.Book;
import com.example.bookstore.mybatis.model.Customorder;
import com.example.bookstore.persistence.mybatis.BooksMyBatisDAO;
import com.example.bookstore.persistence.mybatis.CustomOrdersMyBatisDAO;
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
public class BooksForCustomOrderMyBatis implements Serializable {

    @Inject
    private CustomOrdersMyBatisDAO customOrdersMyBatisDAO;

    @Inject
    private BooksMyBatisDAO booksMyBatisDAO;

    @Getter
    @Setter
    private Customorder customOrder;

    @Getter
    @Setter
    private Book bookToAdd = new Book();

    @Getter
    @Setter
    private List<SelectItem> allBooks;

    @Transactional
    public String createBookFromDropdown() {
        List<Customorder> allBookCustomOrders = bookToAdd.getCustomorders();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomorders(allBookCustomOrders);
        booksMyBatisDAO.update(bookToAdd);
        return "/myBatis/books?faces-redirect=true&customOrderId=" + this.customOrder.getId();
    }

    @Transactional
    public String createBook() {
        Book foundBook = booksMyBatisDAO.findByTitle(bookToAdd.getTitle());
        if (isNull(foundBook)) {
            handleNewBook();
        }
        else {
            handleExistingBook(foundBook);
        }

        return "/myBatis/books?faces-redirect=true&customOrderId=" + this.customOrder.getId();
    }

    private void handleExistingBook(Book foundBook) {
        bookToAdd.setId(foundBook.getId());
        List<Customorder> allBookCustomOrders = foundBook.getCustomorders();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomorders(allBookCustomOrders);
        booksMyBatisDAO.persist(bookToAdd);
    }

    private void handleNewBook() {
        List<Customorder> allBookCustomOrders = new ArrayList<>();
        allBookCustomOrders.add(this.customOrder);
        bookToAdd.setCustomorders(allBookCustomOrders);
        booksMyBatisDAO.persist(bookToAdd);
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int customOrderId = Integer.parseInt(requestParams.get("customOrderId"));
        this.customOrder = customOrdersMyBatisDAO.findOne(customOrderId);
        this.allBooks = new ArrayList<>();
        List<Book> availableBooks = booksMyBatisDAO.findAll();
        for (Book book : availableBooks) {
            this.allBooks.add(new SelectItem(book, book.getTitle()));
        }
    }
}

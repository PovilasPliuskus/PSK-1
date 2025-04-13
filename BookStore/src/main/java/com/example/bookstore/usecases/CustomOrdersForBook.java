package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.persistence.BooksDAO;
import com.example.bookstore.persistence.CustomOrdersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CustomOrdersForBook implements Serializable {

    @Inject
    private CustomOrdersDAO customOrdersDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter
    @Setter
    private Book book;

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int bookId = Integer.parseInt(requestParams.get("bookId"));
        this.book = booksDAO.findOne(bookId);
    }
}

package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.mybatis.model.Book;
import com.example.bookstore.persistence.mybatis.BooksMyBatisDAO;
import com.example.bookstore.persistence.mybatis.CustomOrdersMyBatisDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class CustomOrdersForBookMyBatis implements Serializable {

    @Inject
    private CustomOrdersMyBatisDAO customOrdersMyBatisDAO;

    @Inject
    private BooksMyBatisDAO booksMyBatisDAO;

    @Getter
    @Setter
    private Book book;

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        int bookId = Integer.parseInt(requestParams.get("bookId"));
        this.book = booksMyBatisDAO.findOne(bookId);
    }
}

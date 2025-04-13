package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.mybatis.model.Book;
import com.example.bookstore.persistence.mybatis.BooksMyBatisDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class BooksMyBatis implements Serializable {

    @Inject
    private BooksMyBatisDAO booksMyBatisDAO;

    @Getter
    private List<Book> allBooks;

    @PostConstruct
    private void init() {
        loadAllBooks();
    }

    private void loadAllBooks() {
        allBooks = booksMyBatisDAO.findAll();
    }
}

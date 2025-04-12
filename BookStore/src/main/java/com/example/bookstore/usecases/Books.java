package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.persistence.BooksDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class Books implements Serializable {

    @Inject
    private BooksDAO booksDAO;

    @Getter
    private List<Book> allBooks;

    @PostConstruct
    private void init() {
        loadAllBooks();
    }

    private void loadAllBooks()
    {
        allBooks = booksDAO.findAll();
    }
}

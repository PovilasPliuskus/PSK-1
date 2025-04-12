package com.example.bookstore.persistence;

import com.example.bookstore.entities.Book;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class BooksDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public void persist(Book book) {
        entityManager.persist(book);
    }

    public List<Book> findAll() {
        return entityManager.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public Book findByTitle(String title) {
        try {
            Query query = entityManager.createNamedQuery("Book.findByTitle");
            query.setParameter("title", title);
            return (Book) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

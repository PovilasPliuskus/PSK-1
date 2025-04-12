package com.example.bookstore.persistence;

import com.example.bookstore.entities.Customer;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CustomersDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    public List<Customer> loadAll() {
        return entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public Customer findOne(int id)
    {
        return entityManager.find(Customer.class, id);
    }
}

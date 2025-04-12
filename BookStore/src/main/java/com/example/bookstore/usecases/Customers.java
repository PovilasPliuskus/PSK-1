package com.example.bookstore.usecases;

import com.example.bookstore.entities.Customer;
import com.example.bookstore.persistence.CustomersDAO;
import lombok.Setter;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class Customers implements Serializable {

    @Inject
    private CustomersDAO customersDAO;

    @Getter
    @Setter
    private Customer customerToCreate = new Customer();

    @Getter
    private List<Customer> allCustomers;

    @PostConstruct
    private void init() { loadAllCustomers(); }

    @javax.transaction.Transactional
    public String createCustomer()
    {
        System.out.println("createCustomer Invoked");
        System.out.println(customerToCreate.getId());
        System.out.println(customerToCreate.getFirstName());
        System.out.println(customerToCreate.getMiddleName());
        System.out.println(customerToCreate.getLastName());
        customersDAO.persist(customerToCreate);
        System.out.println("After persist - ID: " + customerToCreate.getId());
        return "index?faces-redirect=true";
    }

    private void loadAllCustomers() {this.allCustomers = customersDAO.loadAll();}
}

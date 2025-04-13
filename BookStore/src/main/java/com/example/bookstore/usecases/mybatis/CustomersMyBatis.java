package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.mybatis.model.Customer;
import com.example.bookstore.persistence.mybatis.CustomersMyBatisDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class CustomersMyBatis implements Serializable {

    @Inject
    private CustomersMyBatisDAO customersMyBatisDAO;

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
        customersMyBatisDAO.persist(customerToCreate);
        return "/myBatis/customers?faces-redirect=true";
    }

    private void loadAllCustomers() {this.allCustomers = customersMyBatisDAO.loadAll();}
}

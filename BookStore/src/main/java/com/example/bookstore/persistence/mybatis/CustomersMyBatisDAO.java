package com.example.bookstore.persistence.mybatis;


import com.example.bookstore.mybatis.dao.CustomerMapper;
import com.example.bookstore.mybatis.model.Customer;
import org.apache.ibatis.session.SqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CustomersMyBatisDAO {

    @Inject
    private SqlSession sqlSession;

    public void persist(Customer customer) {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.insert(customer);
    }

    public Customer findOne(int id) {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public List<Customer> loadAll() {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        return mapper.selectAll();
    }
}

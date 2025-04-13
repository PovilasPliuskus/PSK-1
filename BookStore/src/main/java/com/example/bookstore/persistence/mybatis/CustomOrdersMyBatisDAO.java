package com.example.bookstore.persistence.mybatis;

import com.example.bookstore.mybatis.dao.CustomorderMapper;
import com.example.bookstore.mybatis.model.Book;
import com.example.bookstore.mybatis.model.Customorder;
import org.apache.ibatis.session.SqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomOrdersMyBatisDAO {

    @Inject
    private SqlSession sqlSession;

    @Transactional(Transactional.TxType.MANDATORY)
    public void persist(Customorder customOrder) {
        CustomorderMapper mapper = sqlSession.getMapper(CustomorderMapper.class);
        mapper.insert(customOrder);
    }

    public List<Customorder> loadAll() {
        CustomorderMapper mapper = sqlSession.getMapper(CustomorderMapper.class);
        return mapper.selectAll();
    }

    public Customorder findOne(int id) {
        CustomorderMapper mapper = sqlSession.getMapper(CustomorderMapper.class);
        Customorder customOrder = mapper.selectByPrimaryKey(id);
        List<Book> books = mapper.selectBooksForOrder(id);
        customOrder.setBooks(books);
        return customOrder;
    }

    public void update(Customorder customOrder) {
        CustomorderMapper mapper = sqlSession.getMapper(CustomorderMapper.class);
        mapper.updateByPrimaryKey(customOrder);
    }

    public List<Customorder> findByCustomerId(int customerId) {
        CustomorderMapper mapper = sqlSession.getMapper(CustomorderMapper.class);
        return mapper.selectByCustomerId(customerId);
    }
}

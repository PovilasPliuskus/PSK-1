package com.example.bookstore.persistence.mybatis;

import com.example.bookstore.mybatis.dao.BookMapper;
import com.example.bookstore.mybatis.model.Book;
import org.apache.ibatis.session.SqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class BooksMyBatisDAO {

    @Inject
    private SqlSession sqlSession;

    public void persist(Book book) {
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.insert(book);
    }

    public List<Book> findAll() {
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        return mapper.selectAll();
    }

    public Book findByTitle(String title) {
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        try {
            return mapper.selectByTitle(title);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Book findOne(int bookId) {
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        return mapper.selectByPrimaryKey(bookId);
    }

    public void update(Book book) {
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.updateByPrimaryKey(book);
    }
}

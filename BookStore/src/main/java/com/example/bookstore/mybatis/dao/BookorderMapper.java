package com.example.bookstore.mybatis.dao;

import com.example.bookstore.mybatis.model.Bookorder;
import java.util.List;

public interface BookorderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOKORDER
     *
     * @mbg.generated Sat Apr 12 13:53:24 EEST 2025
     */
    int insert(Bookorder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOKORDER
     *
     * @mbg.generated Sat Apr 12 13:53:24 EEST 2025
     */
    List<Bookorder> selectAll();
}
package com.example.bookstore.mybatis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Customorder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CUSTOMORDER.ID
     *
     * @mbg.generated Sun Apr 13 15:23:54 EEST 2025
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CUSTOMORDER.PAYMENTMETHOD
     *
     * @mbg.generated Sun Apr 13 15:23:54 EEST 2025
     */
    private String paymentMethod;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CUSTOMORDER.FKCUSTOMERID
     *
     * @mbg.generated Sun Apr 13 15:23:54 EEST 2025
     */
    private Integer fkcustomerid;

    private Customer customer;

    List<Book> books = new ArrayList<>();

    public Customorder(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public Customorder(String paymentMethod, Customer customer) {
        this.paymentMethod = paymentMethod;
        this.customer = customer;
    }
}
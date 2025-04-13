package com.example.bookstore.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class BookPriceGenerator implements Serializable {

    public Float generateBookPrice() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        return new Random().nextFloat() * (100);
    }
}

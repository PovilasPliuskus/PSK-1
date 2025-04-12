package com.example.bookstore.persistence;

import com.example.bookstore.entities.CustomOrder;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomOrdersDAO {

    @Setter
    @Inject
    private EntityManager entityManager;

    public List<CustomOrder> loadAll() {
        return entityManager.createNamedQuery("CustomOrder.findAll", CustomOrder.class).getResultList();
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public void persist(CustomOrder customOrder) {
        entityManager.persist(customOrder);
    }

    public CustomOrder findOne(int id) {
        return entityManager.find(CustomOrder.class, id);
    }

    public void update(CustomOrder customOrder) {
        entityManager.merge(customOrder);
    }
}

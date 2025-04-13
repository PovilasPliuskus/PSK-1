package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.CustomOrder;
import com.example.bookstore.persistence.BooksDAO;
import com.example.bookstore.persistence.CustomOrdersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateBookPrice implements Serializable {

    private CustomOrder customOrder;

    @Inject
    private CustomOrdersDAO customOrdersDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        Integer customOrderId = Integer.parseInt(requestParameters.get("customOrderId"));
        this.customOrder = customOrdersDAO.findOne(customOrderId);
        System.out.println("customOrderId " + customOrderId);
    }

    @Transactional
    public String updateBookPrice() {
        try {
        } catch (OptimisticLockException e) {
            return "/books.xhtml?faces-redirect=true&customOrderId=" + this.customOrder.getId() + "&error=optimistic-lock-exception";
        }
        return "customOrders.xhtml?customerId=" + this.customOrder.getCustomer().getId() + "&faces-redirect=true";
    }
}

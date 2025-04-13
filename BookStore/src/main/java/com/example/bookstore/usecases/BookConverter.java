package com.example.bookstore.usecases;

import com.example.bookstore.entities.Book;
import com.example.bookstore.persistence.BooksDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class BookConverter implements Converter {

    @Inject
    private BooksDAO booksDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        return booksDAO.findByTitle(submittedValue);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof Book) {
            return ((Book) o).getTitle();
        }
        return "";
    }
}

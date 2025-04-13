package com.example.bookstore.usecases.mybatis;

import com.example.bookstore.entities.Book;
import com.example.bookstore.persistence.mybatis.BooksMyBatisDAO;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@Model
public class BookConverterMyBatis implements Converter {

    @Inject
    private BooksMyBatisDAO booksMyBatisDAO;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        return booksMyBatisDAO.findByTitle(submittedValue);
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

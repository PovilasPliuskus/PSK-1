package com.example.bookstore.usecases;

import com.example.bookstore.services.BookPriceGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateBookPriceNumber implements Serializable {

    @Inject
    BookPriceGenerator bookPriceGenerator;

    private CompletableFuture<Float> bookPriceGenerationTask = null;

    public String generateNewBookPrice() {
        Map<String, String> requestParams = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        bookPriceGenerationTask = CompletableFuture.supplyAsync(() -> bookPriceGenerator.generateBookPrice());

        return "/books.xhtml?faces-redirect=true&carId=" + requestParams.get("carId");
    }

    public boolean isBookPriceGenerationRunning() {
        return bookPriceGenerationTask != null && !bookPriceGenerationTask.isDone();
    }

    public String getBookPriceGenerationStatus() throws ExecutionException, InterruptedException {
        if (bookPriceGenerationTask == null) {
            return null;
        } else if (isBookPriceGenerationRunning()) {
            return "Book generation in progress";
        }
        return "Suggested book price: " + bookPriceGenerationTask.get();
    }

}

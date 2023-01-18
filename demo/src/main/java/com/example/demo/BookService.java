package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("harry potter and the philosopher's stone","J.K. Rowling", "1997"),
            new Book("harry potter and the chamber of secrets book","J.K. Rowling", "1998")
    ));

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }
}

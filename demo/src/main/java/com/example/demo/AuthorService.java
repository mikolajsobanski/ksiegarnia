package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService {

    private List<Author> authors = new ArrayList<>(Arrays.asList(
            new Author("Mikołaj","Sobański")
    ));

    public Author getAuthor(){
        return new Author(
                "Mikołaj",
                "Sobański"
        );
    }

    public List<Author> getAuthors(){
        return authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }
}

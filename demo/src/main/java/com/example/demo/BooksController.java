package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Validated
@RestController
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping("/postBook")
    public ResponseEntity<Void> addBook(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder){
        this.bookService.addBook(book);
        if(book.getTitle() == null || book.getAuthor() == null || book.getYear() == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        }
        return ResponseEntity
                .created(uriComponentsBuilder.path("/postBook/{title}").build(book.getTitle()))
                .build();
    }
}

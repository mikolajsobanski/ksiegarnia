package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/name")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
         this.authorService = authorService;
     }

     @GetMapping
    public Author getAuthor(){
        return authorService.getAuthor();
     }

     @GetMapping("/names")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
     }

     @PostMapping("/add")
    public void addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
     }

     @RequestMapping(method = RequestMethod.POST, value = "/requestAdd")
    public void addRequestAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
     }
}

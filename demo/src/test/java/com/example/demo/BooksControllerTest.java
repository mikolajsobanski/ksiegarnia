package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mvc);
    }

    @Test
    void getBooks() throws Exception {
        when(bookService.getBooks()).thenReturn(List.of(
                new Book("harry potter and the philosopher's stone","J.K. Rowling", "1997"),
                new Book("harry potter and the chamber of secrets book","J.K. Rowling", "1998")
        ));
        this.mvc.perform(MockMvcRequestBuilders.get("/getBooks")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("harry potter and the philosopher's stone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("J.K. Rowling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].year").value("1997"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("harry potter and the chamber of secrets book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author").value("J.K. Rowling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].year").value("1998"));
    }



    @Test
    void addBook() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders
                .post("/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"harry\", \"author\": \"J.K.Rowlling\", \"year\": \"1999\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString("harry")));
        verify(bookService).addBook(any(Book.class));
    }

    @Test
    void addBookShoudReturn404Code() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders
                        .post("/postBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"harry\", \"author\": \"J.K.Rowlling\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(bookService).addBook(any(Book.class));
    }
}

package com.controller;

import com.contract.data.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.masterdata.Author;
import com.model.masterdata.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {


    @MockBean
    @Qualifier("bookService")
    private IBookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /books/1 - Found")
    void testGetBookByIdFound() throws Exception {
        // Setup our mocked service
        Book mockBook = new Book(1L, "Book Title", "Book Genre", 10d, new Author(1L, "Pippo"));
        //doReturn(Optional.of(mockBook)).when(bookService).findById(1L);
        doReturn(mockBook).when(bookService).findById(1L);

        // Execute the GET request
        mockMvc.perform(get("/books/{id}", 1L))

          // Validate the response code and content type
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

          // Validate the headers
          .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
          .andExpect(header().string(HttpHeaders.LOCATION, "/books/1"))

          // Validate the returned fields
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.title", is("Book Title")))
          .andExpect(jsonPath("$.genre", is("Book Genre")))
          .andExpect(jsonPath("$.price", is(10d)))
          .andExpect(jsonPath("$.author.id", is(1)));
    }


    @Test
    @DisplayName("GET /books/1 - Not Found")
    void testGetBookByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(new Book()).when(bookService).findById(1L);
        // Execute the GET request
        mockMvc.perform(get("/books/{id}", 1L))

          // Validate that we get a 404 Not Found response
          .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST /books - Success")
    void testCreateBook() throws Exception {
        // Setup mocked service
        Author author = new Author(1L, "Pippo");
        Book postBook = new Book("Book Title", "Book Genre", 10d, author);
        Book mockBook = new Book(1L, "Book Title", "Book Genre", 10d, author);
        doReturn(mockBook).when(bookService).setBook(any());

        mockMvc.perform(post("/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(asJsonString(postBook)))

          // Validate the response code and content type
          .andExpect(status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

          // Validate the headers
          .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
          .andExpect(header().string(HttpHeaders.LOCATION, "/books/1"))

          // Validate the returned fields
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.title", is("Book Title")))
          .andExpect(jsonPath("$.genre", is("Book Genre")))
          .andExpect(jsonPath("$.price", is(10d)))
          .andExpect(jsonPath("$.author.id", is(1)));
    }

    @Test
    @DisplayName("PUT /books - Success")
    void testBookPutSuccess() throws Exception {
        // Setup mocked service
        Author author = new Author(1L, "Pippo");
        Book postBook = new Book(1L, "Book Title", 10d, author);
        Book mockBook = new Book(1L, "Book Title", "Book Genre", 10d, author);
        doReturn(mockBook).when(bookService).findById(1L);
        doReturn(mockBook).when(bookService).setBook(any());

        mockMvc.perform(post("/books", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(asJsonString(postBook)))

          // Validate the response code and content type
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

          // Validate the headers
          .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
          .andExpect(header().string(HttpHeaders.LOCATION, "/books/1"))

          // Validate the returned fields
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.title", is("Book Title")))
          .andExpect(jsonPath("$.genre", is("Book Genre")))
          .andExpect(jsonPath("$.price", is(10d)))
          .andExpect(jsonPath("$.author.id", is(1)));
    }

    @Test
    @DisplayName("PUT /books - Not Found")
    void testBookPutNotFound() throws Exception {
        // Setup mocked service
        Book postBook = new Book();
        doReturn(postBook).when(bookService).findById(1L);

        mockMvc.perform(post("/books", 1)
          .contentType(MediaType.APPLICATION_JSON)
          .content(asJsonString(postBook)))

          // Validate the response code and content type
          .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("DELETE /books/1 - Success")
    void testBookDeleteSuccess() throws Exception {
        // Setup mocked product
        Book mockBook = new Book(1L, "Book Title", "Book Genre", 10d, new Author(1L, "Pippo"));
        doReturn(mockBook).when(bookService).findById(1L);
        doReturn(true).when(bookService).delete(1L);

        // Execute our DELETE request
        mockMvc.perform(delete("/books/{id}", 1))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /books/1 - Failure")
    void testBookDeleteFailure() throws Exception {
        // Setup mocked product
        Book mockBook = new Book(1L, "Book Title", "Book Genre", 10d, new Author(1L, "Pippo"));
        doReturn(mockBook).when(bookService).findById(1L);
        doReturn(false).when(bookService).delete(1L);

        // Execute our DELETE request
        mockMvc.perform(delete("/books/{id}", 1))
          .andExpect(status().isInternalServerError());
    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


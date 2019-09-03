package com.controller;

import com.contract.IBookService;
import com.model.masterdata.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final static Logger log = LoggerFactory.getLogger(BookController.class.getName());

    @Autowired
    IBookService bookService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBookList() {

        try {
            List<Book> bookList = bookService.findAll();
            return bookList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Book>();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        log.info("Token received: " + token);

        try {
            Book book = bookService.findById(id);
            if (book.getId() != null)
                return ResponseEntity.ok()
                  .eTag(Long.toString(book.getId()))
                  .location(new URI("/books/" + book.getId().longValue()))
                  .body(book);
            else
                return ResponseEntity.notFound().build();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Book> setBook(@RequestBody Book book) {

        try {

            //For assert that IsCreated or Updated
            boolean bookInsert = true;
            if (book.getId() != null)
                bookInsert = false;

            book = bookService.setBook(book);

            if (bookInsert)
                return ResponseEntity
                  .created(new URI("/books/" + book.getId().longValue()))
                  .eTag(Long.toString(book.getId()))
                  .body(book);

            else
                return ResponseEntity.ok()
                  .location(new URI("/books/" + book.getId().longValue()))
                  .eTag(Long.toString(book.getId()))
                  .body(book);


        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            if (bookService.delete(id))
                return ResponseEntity.ok().build();
            else throw new Exception();
        } catch (Exception ex) {
            log.error("error: " + ex.toString());
            log.error("Book Not Found!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

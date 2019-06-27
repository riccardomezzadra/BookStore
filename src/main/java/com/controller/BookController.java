package com.controller;

import com.contract.data.IBookService;
import com.model.data.BookDAO;
import com.model.masterdata.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final static Logger log = LoggerFactory.getLogger(BookController.class.getName());
    private BookDAO bookDAO = new BookDAO();

    @Autowired
    IBookService bookService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBookList() {

        try {
            List<Book> bookList = bookService.getAll();
            return bookList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Book>();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Long id) {

        try {
            Book book = bookService.getById(id);
            return book;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new Book();
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book setBook(@RequestBody Book book) {

        try {
            book = bookService.setBook(book);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return book;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public Boolean deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.delete(id);
        } catch (Exception ex) {
            log.error("error: " + ex.toString());
            return false;
        }

        return true;
    }
}

package com.controller;

import com.model.data.BookDAO;
import com.model.data.DataIdRequest;
import com.model.masterdata.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final static Logger log = LoggerFactory.getLogger(BookController.class.getName());
    BookDAO bookDAO = new BookDAO();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBookList() {

        try {
            List<Book> bookList = bookDAO.getAll(null);
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
            DataIdRequest req = new DataIdRequest();
            req.setId(id);
            Book book = bookDAO.getElement(req);
            return book;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new Book();

    }

}

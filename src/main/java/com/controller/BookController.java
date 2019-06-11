package com.controller;

import com.model.data.BookDAO;
import com.model.masterdata.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    BookDAO bookDAO = new BookDAO();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBookList() {

        return bookDAO.getAll(null);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Long id) {

        return bookDAO.getElement(null);

    }

}

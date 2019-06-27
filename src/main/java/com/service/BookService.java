package com.service;

import com.contract.data.IBookService;
import com.model.data.BookDAO;
import com.model.data.DataIdRequest;
import com.model.masterdata.Book;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    private final static Logger log = Logger.getLogger(BookService.class.getName());
    private BookDAO bookDAO = new BookDAO();

    public List<Book> getAll() {

        try {
            List<Book> bookList = bookDAO.getAll(null);
            return bookList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Book>();

    }

    public Book getById(Long id) {
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

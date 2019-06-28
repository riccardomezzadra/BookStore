package com.service;

import com.contract.data.IBookService;
import com.model.data.BookDAO;
import com.model.data.DataIdRequest;
import com.model.masterdata.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    private final static Logger log = LogManager.getLogger(BookService.class);
    private BookDAO bookDAO = new BookDAO();

    public List<Book> getAll() {

        try {
            List<Book> bookList = bookDAO.getAll(null);
            log.debug("DEBUG!!!!!!");
            log.info("INFO!!!!!!!!");
            log.fatal("FATAL!!!!");
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


    public Book setBook(Book book) {

        try {
            bookDAO.setElement(book);
            log.info(book.getTitle());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return book;
    }

    public boolean delete(Long id) {

        try {
            bookDAO.deleteElement(id);
        } catch (Exception ex) {
            log.error("error: " + ex.toString());
            return false;
        }
        return true;
    }

}

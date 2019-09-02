package com.service;

import com.contract.IAuthorService;
import com.model.data.AuthorDAO;
import com.model.data.DataIdRequest;
import com.model.masterdata.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    private final static Logger log = LogManager.getLogger(AuthorService.class);
    private AuthorDAO authorDAO = new AuthorDAO();

    public List<Author> findAll() {
        try {
            List<Author> authorList = authorDAO.getAll(null);
            return authorList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Author>();
    }

    public Author findById(Long id) {
        try {
            DataIdRequest req = new DataIdRequest();
            req.setId(id);
            Author author = authorDAO.getElement(req);
            return author;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new Author();
    }

    public Author setAuthor(Author author) {
        try {
            authorDAO.setElement(author);
            log.info(author.getName());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return author;
    }

    public boolean delete(Long id) {
        try {
            authorDAO.deleteElement(id);
        } catch (Exception ex) {
            log.error("error: " + ex.toString());
            return false;
        }
        return true;
    }
}

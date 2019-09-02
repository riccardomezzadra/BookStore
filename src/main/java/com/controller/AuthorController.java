package com.controller;


import com.contract.IAuthorService;
import com.model.data.AuthorDAO;
import com.model.masterdata.Author;
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
public class AuthorController {


    private final static Logger log = LoggerFactory.getLogger(AuthorController.class.getName());
    private AuthorDAO authorDAO = new AuthorDAO();

    @Autowired
    IAuthorService authorService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public List<Author> getAuthorList() {

        try {
            List<Author> authorList = authorService.findAll();
            return authorList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Author>();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {

        try {
            // call service
            Author author = authorService.findById(id);
            if (author.getId() != null)
                return ResponseEntity.ok()
                  .eTag(Long.toString(author.getId()))
                  .location(new URI("/authors/" + author.getId().longValue()))
                  .body(author);
            else
                return ResponseEntity.notFound().build();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public ResponseEntity<Author> setAuthor(@RequestBody Author author) {

        try {
            //For assert that IsCreated or Updated
            boolean authorInsert = true;
            if (author.getId() != null)
                authorInsert = false;

            author = authorService.setAuthor(author);

            if (authorInsert)
                return ResponseEntity
                  .created(new URI("/authors/" + author.getId().longValue()))
                  .eTag(Long.toString(author.getId()))
                  .body(author);

            else
                return ResponseEntity.ok()
                  .location(new URI("/authors/" + author.getId().longValue()))
                  .eTag(Long.toString(author.getId()))
                  .body(author);


        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        try {
            if (authorService.delete(id))
                return ResponseEntity.ok().build();
            else throw new Exception();
        } catch (Exception ex) {
            log.error("error: " + ex.toString());
            log.error("Author Not Found!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

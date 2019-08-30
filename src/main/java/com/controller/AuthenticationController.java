package com.controller;

import com.contract.data.IAuthenticationService;
import com.model.Authentication.Account;
import com.model.Authentication.AuthToken;
import com.model.exceptions.HTTPResponseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthenticationController {

    private final static Logger log = Logger.getLogger(AuthenticationController.class);

    @Autowired
    IAuthenticationService authenticationService;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthToken login(@RequestBody Account credentials) throws HTTPResponseException {

        AuthToken token;
        try {
            // Account account = authenticationService.findById(credentials.getId());
            Account account = authenticationService.findByName(credentials.getUsername().toLowerCase());
            token = authenticationService.login(account, credentials);
            return token;
        } catch (Exception ex) {
            log.equals(ex.getMessage());
        }
        return null;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<Account> getAccountList() {

        try {
            List<Account> accountList = authenticationService.findAll();
            return accountList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return new ArrayList<Account>();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id) {

        try {
            Account account = authenticationService.findById(id);
            if (account.getId() != null)
                return ResponseEntity.ok()
                  .eTag(Long.toString(account.getId()))
                  .location(new URI("/accounts/" + account.getId().longValue()))
                  .body(account);
            else
                return ResponseEntity.notFound().build();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Account signup(@RequestBody Account account) throws HTTPResponseException {
        try {
            authenticationService.signup(account);
        } catch (Exception ex) {
            log.error("Signup Failed: " + ex.getMessage());
            throw new HTTPResponseException("Account already exists!", HttpStatus.LOCKED);
        }
        return account;
    }

}

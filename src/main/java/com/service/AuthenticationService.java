package com.service;

import com.contract.data.IAuthenticationService;
import com.model.Authentication.Account;
import com.model.Authentication.AuthToken;
import com.model.data.AccountDAO;
import com.model.data.AuthTokenDAO;
import com.model.data.DataIdRequest;
import com.model.data.DataValueRequest;
import com.model.exceptions.HTTPResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final static Logger log = LogManager.getLogger(AuthenticationService.class);

    AccountDAO accountDAO = new AccountDAO();
    AuthTokenDAO authTokenDAO = new AuthTokenDAO();

    public boolean checkToken(String token) {

        log.info("Token received: " + token);

        try {
            AuthToken authToken = null;
            DataValueRequest req = new DataValueRequest();
            req.setId(token);
            authToken = authTokenDAO.verifyToken(req);

            if ((authToken != null) && (authToken.getExpiryDate().isAfter(LocalDateTime.now()))) {
                return true;
            } else {
                // TODO: add exception
                throw new HTTPResponseException("Bad credentials", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return false;
    }

    public List<Account> findAll() {

        try {
            List<Account> accountList = accountDAO.getAll(null);
            return accountList;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return new ArrayList<Account>();

    }

    public Account findById(Long id) {

        try {
            DataIdRequest req = new DataIdRequest();
            req.setId(id);
            Account account = accountDAO.getElement(req);
            return account;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public Account findByName(String name) {

        try {
            DataValueRequest req = new DataValueRequest();
            req.setFieldName(name);
            Account account = accountDAO.getElement(req);
            return account;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public AuthToken login(Account account, Account credentials) {

        AuthToken token;

        if (credentials != null) {
            String passwordToMatch = account.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordToMatch != null && passwordEncoder.matches(credentials.getPassword(), passwordToMatch)) {
                //token = generateAuthToken(credentials);
                token = generateAuthToken(account);

            } else {
                throw new HTTPResponseException("Bad credentials", HttpStatus.UNAUTHORIZED);
            }

        } else {
            throw new HTTPResponseException("Bad credentials", HttpStatus.UNAUTHORIZED);
        }
        sanitiseAccount(token.getAccount());
        return token;
    }

    public Account signup(Account account) {
        try {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            account.setUsername(account.getUsername().toLowerCase());

            accountDAO.setElement(account);
            sanitiseAccount(account);

        } catch (Exception ex) {
            throw ex;
        }

        return account;
    }


    private void sanitiseAccount(Account account) {
        account.setPassword("*******");
    }

    private AuthToken generateAuthToken(Account account) {
        AuthToken token = AuthToken.createInstance(UUID.randomUUID().toString(), account);
        authTokenDAO.setElement(token);
        return token;
    }

}

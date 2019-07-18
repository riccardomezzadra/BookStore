package com.service;

import com.contract.data.IAuthenticationService;
import com.model.Authentication.Account;
import com.model.data.AccountDAO;
import com.model.data.AuthTokenDAO;
import com.model.data.DataIdRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final static Logger log = LogManager.getLogger(AuthenticationService.class);

    AccountDAO accountDAO = new AccountDAO();
    AuthTokenDAO authTokenDAO = new AuthTokenDAO();

    public boolean checkToken(String token) {
        return true;
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
        return new Account();
    }


    public Account signup(Account account) {
        try {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPassword(passwordEncoder.encode(account.getPassword()));

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

}

package com.contract.data;

import com.model.Authentication.Account;

import java.util.List;

public interface IAuthenticationService {

    List<Account> findAll();

    Account findById(Long id);

    //Account setBook(Account book);

    //boolean delete(Long id);

    boolean checkToken(String token);

}
package com.model.Authentication;

import com.contract.IBaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthToken implements IBaseModel {

    private Long id;
    private String token;
    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private Account account;

    public AuthToken() {

    }

    private AuthToken(String token, Account account) {
        this.token = token;
        this.account = account;
        this.creationDate = LocalDateTime.now();
        this.expiryDate = creationDate.plusDays(10);
    }

    public static AuthToken createInstance(String token, Account account) {
        return new AuthToken(token, account);
    }
}

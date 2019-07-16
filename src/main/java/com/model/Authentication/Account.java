package com.model.Authentication;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Account {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Date creationDate;
}

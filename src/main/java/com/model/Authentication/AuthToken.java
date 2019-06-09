package com.model.Authentication;

import com.model.masterdata.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AuthToken {

    private Long id;
    private String token;
    private Date creationDate;
    private Date expiryDate;
    private Customer customer;
}

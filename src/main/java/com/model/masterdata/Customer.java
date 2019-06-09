package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String userCode;
    private String photoURL;
    private String fiscalCode;
    private String status;
    private Date creationDate;
    private Date lastUpdateDate;
    private Date deactivationDate;
    private Double portfolio;
    private String gender;
    private String role;
    private Boolean enabled;


}

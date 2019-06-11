package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Author {

    private Long id;
    private String name;
    private String surname;
    private String photoURL;
    private String nationality;
    private String birthPlace;
    private Date dateOfBirth;
    private Date dateOfDeath;
    private String gender;

}

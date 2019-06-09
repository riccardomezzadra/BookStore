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
    private Date DateOfBirth;
    private Date DateOfDeath;
    private String gender;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", DateOfDeath=" + DateOfDeath +
                ", gender='" + gender + '\'' +
                '}';
    }
}

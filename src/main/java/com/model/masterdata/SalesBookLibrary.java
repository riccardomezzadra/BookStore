package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalesBookLibrary {

    private Long id;
    private BookStore bookStore;
    private Book book;
    private Integer bookQuantity;


}



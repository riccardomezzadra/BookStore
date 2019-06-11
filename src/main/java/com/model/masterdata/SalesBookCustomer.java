package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SalesBookCustomer {

    private Long id;
    private Integer quantity;
    private Double totalAmount;
    private Double unitPrice;
    private Customer customer;
    private Book book;

}

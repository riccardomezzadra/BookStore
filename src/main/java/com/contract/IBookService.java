package com.contract;

import com.model.masterdata.Book;

import java.util.List;

public interface IBookService {

    List<Book> findAll();

    Book findById(Long id);

    Book setBook(Book book);

    boolean delete(Long id);

}

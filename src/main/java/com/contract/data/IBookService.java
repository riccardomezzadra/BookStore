package com.contract.data;

import com.model.masterdata.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();

    Book getById(Long id);

    //Book insert(Book book);

    //Book update(Book book);

    //void delete(Long id);

}

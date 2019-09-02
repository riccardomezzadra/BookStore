package com.contract;

import com.model.masterdata.Author;

import java.util.List;

public interface IAuthorService {

    List<Author> findAll();

    Author findById(Long id);

    Author setAuthor(Author author);

    boolean delete(Long id);
}

package com.model.data;

import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.masterdata.Author;
import com.model.masterdata.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBaseDAO<Book, IRequest> {


    public List<Book> getAll(IRequest req) {

        //Create list's book
        List<Book> listBook = new ArrayList<Book>();

        //Create Author
        Author author = new Author();

        author.setName("Pippo");
        author.setSurname("Pippa");
        author.setPhotoURL("pippo.jpeg");
        author.setNationality("EN");
        author.setBirthPlace("London");
        //Sistemare DATE
        author.setDateOfBirth(new java.util.Date(System.currentTimeMillis()));
        author.setDateOfDeath(null);
        author.setGender("M");

        //Create new book objects
        //Book1
        Book book1 = new Book();

        book1.setTitle("LibroProva1");
        book1.setGenre("Novel");
        book1.setPrice(47.5d);
        book1.setAuthor(author);

        //Book2
        Book book2 = new Book();

        book2.setTitle("TestBook");
        book2.setGenre("novel");
        book2.setPrice(40.5d);
        book2.setAuthor(author);

        //Book3
        Book book3 = new Book();

        book3.setTitle("LibroProva3");
        book3.setGenre("poem");
        book3.setPrice(40.5d);
        book3.setAuthor(author);

        //Book4
        Book book4 = new Book();

        book4.setTitle("SignorinoDegliAgnelli");
        book4.setGenre("other");
        book4.setPrice(21.5d);
        book4.setAuthor(author);

        //Add books to listBook
        listBook.add(book1);
        listBook.add(book2);
        listBook.add(book3);
        listBook.add(book4);

        return listBook;

    }

    public Book getElement(IRequest req) {
        return null;
    }


    //For Date format !
    /*
    static  private String formatDate(Date dateToConvert)
    {
        return dateToConvert.toInstant().atZone(ZoneId.of("Europe/Rome")).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    */

}

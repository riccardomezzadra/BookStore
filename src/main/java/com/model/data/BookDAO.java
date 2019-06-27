package com.model.data;

import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.masterdata.Author;
import com.model.masterdata.Book;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookDAO implements IBaseDAO<Book, IRequest> {

    private final static Logger log = Logger.getLogger(BookDAO.class.getName());
    SqlSessionFactory sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();

/*
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
*/

    public List<Book> getAll(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        List list = new ArrayList<>();

        try {
            list = session.selectList("Book.selectAll");
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
        log.info("Books got from db: " + list.size());

        return list;

    }


    public Book getElement(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        Book book = null;

        try {
            book = session.selectOne("Book.selectById", req instanceof DataIdRequest ? ((DataIdRequest) req).getId() : null);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }

        log.info(book.getTitle());
        return book;
    }

    public Book setElement(Book book) {
        SqlSession session = sqlSessionFactory.openSession();

        try {

            if (book.getId() == null) {
                session.insert("Book.insert", book);
            } else
                session.update("Book.update", book);

        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }

        return book;
    }

    public void deleteElement(Long id) {
        SqlSession session = sqlSessionFactory.openSession();
        DataIdRequest req = new DataIdRequest();
        req.setId(id);
        try {
            session.delete("Book.delete", req);
            log.info("Removed book n. " + String.valueOf(id.longValue()) + " from Book");
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }
    }

    //For Date format !
    /*
    static  private String formatDate(Date dateToConvert)
    {
        return dateToConvert.toInstant().atZone(ZoneId.of("Europe/Rome")).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    */

}

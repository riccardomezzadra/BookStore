package com.model.data;

import com.contract.AbstractDAO;
import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.masterdata.Author;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AuthorDAO extends AbstractDAO implements IBaseDAO<Author, IRequest> {


    private final static Logger log = Logger.getLogger(AuthorDAO.class.getName());
    SqlSessionFactory sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();

    @Override
    public List<Author> getAll(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        List list = new ArrayList<>();

        try {
            list = session.selectList("Author.selectAll");
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
        log.info("Authors got from db: " + list.size());

        return list;

    }

    public Author getElement(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        Author author = null;
        try {
            author = session.selectOne("Author.selectById", req instanceof DataIdRequest ? ((DataIdRequest) req).getId() : null);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }

        log.info(author.getName());
        return author;
    }

    public Author setElement(Author author) {
        SqlSession session = sqlSessionFactory.openSession();

        try {

            if (author.getId() == null) {
                session.insert("Author.insert", author);
            } else
                session.update("Author.update", author);

        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }

        return author;
    }

    public void deleteElement(Long id) {
        SqlSession session = sqlSessionFactory.openSession();
        DataIdRequest req = new DataIdRequest();
        req.setId(id);
        try {
            session.delete("Author.delete", req);
            log.info("Removed author n. " + String.valueOf(id.longValue()) + " from Author");
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }

    }


}

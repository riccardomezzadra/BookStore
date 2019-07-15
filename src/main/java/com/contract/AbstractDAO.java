package com.contract;

import com.model.data.ConnectionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T extends IBaseModel> {

    protected SqlSessionFactory sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    protected final static Logger log = Logger.getLogger(AbstractDAO.class.getName());
    private SqlSession session;

    private List<T> selectList(String selectId, Object req) {

        session = sqlSessionFactory.openSession();
        List<T> list = new ArrayList();

        try {
            list = session.selectList(selectId, req);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
        return list;
    }

    protected List selectAllList(String selectId) {
        return this.selectList(selectId + ".selectAll", null);
    }

    protected List searchList(String selectId, Object req) {
        return this.selectList(selectId, req);
    }

    protected T selectOne(String selectId, Object req) {

        session = sqlSessionFactory.openSession();
        T one;

        try {
            one = session.selectOne(selectId, req);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }
        return one;
    }

    public T setElement(T element) {

        SqlSession session = null;

        try {

            session = sqlSessionFactory.openSession();

            if (element.getId() <= 0) {
                session.insert(element.getClass().getSimpleName() + ".insert", element);
                log.info(element.getClass().getName() + " " + element.getId() + " inserted successfully!");
            } else {
                session.update(element.getClass().getSimpleName() + ".update", element);
                log.info(element.getClass().getName() + " " + element.getId() + " updated successfully!");
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
        return element;
    }

}

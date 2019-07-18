package com.model.data;

import com.contract.AbstractDAO;
import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.Authentication.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AccountDAO extends AbstractDAO implements IBaseDAO<Account, IRequest> {

    private final static Logger log = Logger.getLogger(BookDAO.class.getName());
    SqlSessionFactory sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();

    public List<Account> getAll(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        List list = new ArrayList<Account>();

        try {
            list = session.selectList("Account.selectAll");
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }

        log.info("Accounts got from db: " + list.size());
        return list;
    }

    public Account getElement(IRequest req) {

        SqlSession session = sqlSessionFactory.openSession();
        Account account = null;

        try {
            account = session.selectOne("Account.selectById", req instanceof DataIdRequest ? ((DataIdRequest) req).getId() : null);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.close();
        }

        log.info(account.getUsername());
        return account;
    }

    public Account setElement(Account account) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            if (account.getId() == null || account.getId() <= 0)
                session.insert("Account.insert", account);
            //else , create update method

        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }

        log.info(account.getUsername());
        return account;

    }


}

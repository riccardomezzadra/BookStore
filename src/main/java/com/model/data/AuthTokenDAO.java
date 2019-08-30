package com.model.data;

import com.contract.AbstractDAO;
import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.Authentication.AuthToken;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AuthTokenDAO extends AbstractDAO<AuthToken> implements IBaseDAO<AuthToken, IRequest> {


    @Override
    public List<AuthToken> getAll(IRequest req) {
        return null;
    }


    @Override
    public AuthToken getElement(IRequest req) {

        AuthToken authToken = selectOne("AuthToken.selectById", req instanceof DataIdRequest ? ((DataIdRequest) req).getId() : null);
        return authToken;

    }


    public AuthToken setElement(AuthToken token) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("AuthToken.insert", token);
        } catch (Exception ex) {
            throw ex;
        } finally {
            session.commit();
            session.close();
        }
        return null;
    }

}

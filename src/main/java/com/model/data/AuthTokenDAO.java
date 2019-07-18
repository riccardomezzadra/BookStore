package com.model.data;

import com.contract.AbstractDAO;
import com.contract.IRequest;
import com.contract.data.IBaseDAO;
import com.model.Authentication.AuthToken;

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

    //Sistemare prima query insert
    public AuthToken setElement(AuthToken token) {
        return null;
    }

}

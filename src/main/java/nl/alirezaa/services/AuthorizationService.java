package nl.alirezaa.services;

import nl.alirezaa.DAO.AuthorizationDao;
import nl.alirezaa.authorization.JWTconnection;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.model.CredentialModel;

import java.sql.SQLException;

public class AuthorizationService {

    private AuthorizationDao authorizationDao;

    public AuthorizationService() { this.authorizationDao = new AuthorizationDao();}

    public AccountModel checkCredentials(CredentialModel credential) throws SQLException, ClassNotFoundException  {
        AccountModel checkedAccount = authorizationDao.checkCredentials(credential);
        checkedAccount.setToken(JWTconnection.getInstance().createToken(checkedAccount));
        return checkedAccount;
    }

}

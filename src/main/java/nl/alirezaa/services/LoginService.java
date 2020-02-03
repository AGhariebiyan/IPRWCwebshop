package nl.alirezaa.services;

import nl.alirezaa.DAO.LoginDao;
import nl.alirezaa.utils.JWTUtils;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.model.CredentialModel;

import java.sql.SQLException;
import java.util.Optional;

public class LoginService {
    private LoginDao loginDao;

    public LoginService() { this.loginDao = new LoginDao();}

    public Optional<AccountModel> checkCredentials(CredentialModel credentials) throws SQLException, ClassNotFoundException {
        AccountModel checkedAccount = null;
        try {
            checkedAccount = loginDao.checkCredentials(credentials);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (checkedAccount != null) {
            checkedAccount.setJwttoken(JWTUtils.getInstance().createToken(checkedAccount));
            return Optional.of(checkedAccount);
//            if (checkedAccount.getPassword().equals(credentials.getPassword())) {
//                checkedAccount.setJwttoken(JWTUtils.getInstance().createToken(checkedAccount));
//                return Optional.of(checkedAccount);
//            }
        }
        return Optional.of(new AccountModel());
    }
}

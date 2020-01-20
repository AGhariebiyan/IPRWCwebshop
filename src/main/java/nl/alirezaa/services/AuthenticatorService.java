package nl.alirezaa.services;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import nl.alirezaa.DAO.AccountDao;
import nl.alirezaa.utils.JWTUtils;
import nl.alirezaa.model.AccountModel;

import java.sql.SQLException;
import java.util.Optional;

public class AuthenticatorService implements Authenticator<String, AccountModel> {
    JWTUtils jwtUtils;
    AccountDao accountDao;

    public AuthenticatorService() {
        this.jwtUtils = new JWTUtils();
        this.accountDao = new AccountDao();
    }

    @Override
    public Optional<AccountModel> authenticate(String jwt) throws AuthenticationException {
        AccountModel account = new AccountModel();

        System.out.println(jwt);

        if (!jwtUtils.verifyJwtToken(jwt)) {
            throw new AuthenticationException("Invalid JWT token");
        }

        System.out.println(" token is good");

        int userId = Integer.parseInt(jwtUtils.retrieveUsernameFromJWToken(jwt));

        System.out.println("got user " + userId);

        try {
            account = this.accountDao.getAccountById(userId);
            System.out.println("good to go");
            return Optional.of(account);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}

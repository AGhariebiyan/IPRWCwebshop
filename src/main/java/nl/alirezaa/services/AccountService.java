package nl.alirezaa.services;

import nl.alirezaa.DAO.AccountDao;
import nl.alirezaa.enums.AccountRole;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.utils.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountDao accountDao;
    public static final String SALT = "my-salt-text";

    public AccountService() { this.accountDao = new AccountDao(); }

    public List<AccountModel> getAllAccounts() throws SQLException, ClassNotFoundException {
        return accountDao.findAllAccounts();
    }

    public void addAccount(AccountModel account) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        System.out.println("AccountService: ");
        if (account.getEmail().contains("@hsleiden.nl")) {
            account.setAccountType(AccountRole.ADMIN.getrole());
        } else {
            account.setAccountType(AccountRole.CUSTOMER.getrole());
        }
        String password = account.getPassword();
        System.out.println(password);
        String saltedPassword = SALT + password;
        System.out.println("salted password" + saltedPassword);
        String hashedPassword = HashUtils.generateHash(saltedPassword);
        account.setPassword(hashedPassword);
        System.out.println("set password" + hashedPassword);
        accountDao.addAccount(account);
    }

    public void deleteAccount(int id) throws SQLException, ClassNotFoundException{
        accountDao.deleteAccount(id);
    }

    public AccountModel getAccountById(int id) throws SQLException, ClassNotFoundException {
        return accountDao.getAccountById(id);
    }

    public void updateAccountById(int id, AccountModel account) throws SQLException, ClassNotFoundException {
        accountDao.updateAccountById(id, account);
    }
}

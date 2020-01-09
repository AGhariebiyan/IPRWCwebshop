package nl.alirezaa.DAO;

import nl.alirezaa.dbConnection.dbConnection;
import nl.alirezaa.model.AccountModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    // Account model aanmaken die nodig is om gegevens bijv op te halen.
    private AccountModel makeModel(ResultSet result) {
        AccountModel account = new AccountModel();
        try {
            account.setUser_id(result.getInt("a_id"));
            account.setEmail(result.getString("a_email"));
            account.setPassword(result.getString("a_password"));
            account.setAccountType(result.getString("a_accountType"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Ophalen alle accounts
    public List<AccountModel> findAllAccounts() throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM account";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<AccountModel> accountList = new ArrayList<>();
        while (resultSet.next()){
            accountList.add(makeModel(resultSet));
        }

        return accountList;
    }

    // account toevoegen
    public void addAccount(AccountModel account) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO account(a_email, a_password, a_accountType) VALUES (?,?,?)";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setString(1, account.getEmail());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getAccountType());

        statement.execute();
    }

    public void deleteAccount(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM account WHERE a_id = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setInt(1, id);

        statement.execute();
    }

    public AccountModel getAccountById(int id) throws SQLException, ClassNotFoundException {
        AccountModel account;

        String query = "SELECT * FROM account WHERE a_id = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            account = makeModel(resultSet);
            return account;
        }
        return null;
    }

    public void updateAccountById (int id, AccountModel account) throws SQLException, ClassNotFoundException {
        String query = "UPDATE product SET a_email = ?, a_password = ?, a_accountType = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setString(1, account.getEmail());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getAccountType());

        statement.execute();
    }

}

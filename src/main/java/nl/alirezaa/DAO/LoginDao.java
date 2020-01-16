package nl.alirezaa.DAO;

import nl.alirezaa.dbConnection.dbConnection;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.model.CredentialModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

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

    public AccountModel checkCredentials(CredentialModel credential) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM account WHERE a_email = ? AND a_password = ?";
        PreparedStatement statement = dbConnection.getInstance().createPreparedStatement(query);
        statement.setString(1, credential.getEmail());
        statement.setString(2, credential.getPassword());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
           AccountModel account = makeModel(resultSet);
            return account;
        }
        return null;
    }
}

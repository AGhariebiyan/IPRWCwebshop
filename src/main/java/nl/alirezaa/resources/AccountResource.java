package nl.alirezaa.resources;

import nl.alirezaa.model.AccountModel;
import nl.alirezaa.services.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/account")
public class AccountResource {
    private AccountService accountService;

    public AccountResource() { this.accountService = new AccountService(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountModel> getAllAccounts() throws SQLException, ClassNotFoundException {
        return accountService.getAllAccounts();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAccount (AccountModel account) throws SQLException, ClassNotFoundException {
        accountService.addAccount(account);
    }

    @Path("/delete/{id}")
    @DELETE
    public void deleteAccount(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        accountService.deleteAccount(id);
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAccountById(@PathParam("id") int id, AccountModel account) throws SQLException, ClassNotFoundException {
        accountService.updateAccountById(id, account);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AccountModel getAccountById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return accountService.getAccountById(id);
    }

}

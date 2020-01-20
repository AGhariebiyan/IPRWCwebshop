package nl.alirezaa.resources;

import io.dropwizard.auth.AuthenticationException;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.services.AccountService;
import nl.alirezaa.services.AuthenticatorService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/account")

public class AccountResource {
    private AccountService accountService;
    private AuthenticatorService authenticatorService;

    public AccountResource() {
        this.accountService = new AccountService();
        this.authenticatorService = new AuthenticatorService();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountModel> getAllAccounts() throws SQLException, ClassNotFoundException, AuthenticationException {
        return accountService.getAllAccounts();
    }

    @RolesAllowed("ADMIN")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAccount (AccountModel account) throws SQLException, ClassNotFoundException {
        accountService.addAccount(account);
    }

    @RolesAllowed("ADMIN")
    @Path("/delete/{id}")
    @DELETE
    public void deleteAccount(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        accountService.deleteAccount(id);
    }

    @RolesAllowed("ADMIN")
    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAccountById(@PathParam("id") int id, AccountModel account) throws SQLException, ClassNotFoundException {
        accountService.updateAccountById(id, account);
    }

    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AccountModel getAccountById(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        return accountService.getAccountById(id);
    }

}

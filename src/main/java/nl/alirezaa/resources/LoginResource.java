package nl.alirezaa.resources;

import nl.alirezaa.model.AccountModel;
import nl.alirezaa.model.CredentialModel;
import nl.alirezaa.services.AuthenticatorService;
import nl.alirezaa.services.LoginService;

import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;

@Path("/login")
public class LoginResource {
    private LoginService loginService;

    public LoginResource() { this.loginService = new LoginService(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<AccountModel> login(CredentialModel credentials) throws SQLException, ClassNotFoundException, AuthenticationException, NoSuchAlgorithmException {
        return loginService.checkCredentials(credentials);
    }
}

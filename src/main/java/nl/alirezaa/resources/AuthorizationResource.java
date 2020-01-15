package nl.alirezaa.resources;

import nl.alirezaa.model.AccountModel;
import nl.alirezaa.model.CredentialModel;
import nl.alirezaa.services.AuthorizationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/login")
public class AuthorizationResource {
    private AuthorizationService authorizationService;

    public AuthorizationResource() { this.authorizationService = new AuthorizationService(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AccountModel login(CredentialModel credential) throws SQLException, ClassNotFoundException {
        return authorizationService.checkCredentials(credential);
    }
}

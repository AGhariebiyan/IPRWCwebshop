package nl.alirezaa.services;
import io.dropwizard.auth.Authorizer;
import nl.alirezaa.model.AccountModel;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class AuthorizerService implements Authorizer<AccountModel> {

    @Override
    public boolean authorize(AccountModel user, String role) {
        return user.getEmail().contains("@hsleiden.nl") && role.equals("ADMIN");
    }

}

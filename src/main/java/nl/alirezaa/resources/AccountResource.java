package nl.alirezaa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("/account")
public class AccountResource {
    private String name = "yo";

    @Path("/name")
    @GET
    public String getName() throws SQLException, ClassNotFoundException {
        return this.name;
    }
}

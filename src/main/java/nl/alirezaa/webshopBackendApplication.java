package nl.alirezaa;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.alirezaa.authorization.JWTconnection;
import nl.alirezaa.resources.AccountResource;
import nl.alirezaa.resources.AuthorizationResource;
import nl.alirezaa.resources.ProductResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class webshopBackendApplication extends Application<webshopBackendConfiguration> {
    private static webshopBackendConfiguration webshopBackendConfiguration;
    public static webshopBackendConfiguration getConfiguration() { return webshopBackendConfiguration; }

    public static void main(final String[] args) throws Exception {
        new webshopBackendApplication().run(args);
    }

    @Override
    public String getName() {
        return "webshopBackend";
    }

    @Override
    public void initialize(final Bootstrap<webshopBackendConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final webshopBackendConfiguration configuration,
                    final Environment environment) {
        //Hierdoor kan de config in hele applicatie gebruikt worden.
        webshopBackendConfiguration = configuration;

        JWTconnection.getInstance().setKey(webshopBackendConfiguration.getSecret());

        // Dit zorgt ervoor dat alles kan ontvangen.
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new ProductResource());
        environment.jersey().register(new AccountResource());
        environment.jersey().register(new AuthorizationResource());
    }

}

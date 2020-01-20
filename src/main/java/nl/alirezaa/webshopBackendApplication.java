package nl.alirezaa;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.alirezaa.utils.JWTUtils;
import nl.alirezaa.model.AccountModel;
import nl.alirezaa.resources.AccountResource;
import nl.alirezaa.resources.LoginResource;
import nl.alirezaa.resources.ProductResource;
import nl.alirezaa.services.AuthenticatorService;
import nl.alirezaa.services.AuthorizerService;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

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

        JWTUtils.getInstance().setKey(webshopBackendConfiguration.getSecret().getBytes());

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
        environment.jersey().register(new LoginResource());

        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<AccountModel>()
                        .setAuthenticator(new AuthenticatorService())
                        .setAuthorizer(new AuthorizerService())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AccountModel.class));
    }

}

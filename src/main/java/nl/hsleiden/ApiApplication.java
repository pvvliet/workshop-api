
package nl.hsleiden;

import com.google.inject.Module;
import com.hubspot.dropwizard.guice.GuiceBundle.Builder;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import nl.hsleiden.model.User;
import nl.hsleiden.service.AuthenticationService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter van Vliet
 */
public class ApiApplication extends Application<ApiConfiguration>
{
    private final Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    
    private ConfiguredBundle assetsBundle;
    private GuiceBundle guiceBundle;
    
    private String name;
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap)
    {
        assetsBundle = (ConfiguredBundle) new ConfiguredAssetsBundle("/assets/", "/client", "index.html");
        guiceBundle = createGuiceBundle(ApiConfiguration.class, new ApiGuiceModule());
        
        bootstrap.addBundle(assetsBundle);
        bootstrap.addBundle(guiceBundle);
    }
    
    @Override
    public void run(ApiConfiguration configuration, Environment environment)
    {
        name = configuration.getApiName();
        
        logger.info(String.format("Set API name to %s", name));
        
        setupAuthentication(environment);
        configureClientFilter(environment);
    }
    
    private GuiceBundle createGuiceBundle(Class<ApiConfiguration> configurationClass, Module module)
    {
        Builder guiceBuilder = GuiceBundle.<ApiConfiguration>newBuilder()
                .addModule(module)
                .enableAutoConfig(new String[] { "nl.hsleiden" })
                .setConfigClass(configurationClass);

        return guiceBuilder.build();
    }
    
    private void setupAuthentication(Environment environment)
    {
        AuthenticationService authenticationService = guiceBundle.getInjector().getInstance(AuthenticationService.class);
        ApiUnauthorizedHandler unauthorizedHandler = guiceBundle.getInjector().getInstance(ApiUnauthorizedHandler.class);
        
        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(authenticationService)
                .setAuthorizer(authenticationService)
                .setRealm("SUPER SECRET STUFF")
                .setUnauthorizedHandler(unauthorizedHandler)
                .buildAuthFilter())
        );
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
    
    private void configureClientFilter(Environment environment)
    {
        environment.getApplicationContext().addFilter(
            new FilterHolder(new ClientFilter()),
            "/*",
            EnumSet.allOf(DispatcherType.class)
        );
    }
    
    public static void main(String[] args) throws Exception
    {
        new ApiApplication().run(args);
    }
}

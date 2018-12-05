package nl.hsleiden.resource;

import java.util.Optional;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
@Path("/")
public class DefaultResource
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@QueryParam("name") Optional<String> name)
    {
        String displayName = name.orElse("world");
        
        return "Hello " + displayName;
    }
}

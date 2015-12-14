package nl.actorius;

import io.dropwizard.auth.UnauthorizedHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Peter van Vliet
 */
public class ApiUnauthorizedHandler implements UnauthorizedHandler
{
    @Override
    public Response buildResponse(String prefix, String realm)
    {
        return Response.status(Response.Status.UNAUTHORIZED)
            .type(MediaType.TEXT_PLAIN_TYPE)
            .entity("Credentials are required to access this resource.")
            .build();
    }
}

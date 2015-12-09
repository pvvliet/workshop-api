package nl.actorius.resource;

import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.actorius.model.User;
import nl.actorius.service.UserService;

/**
 *
 * @author Peter van Vliet
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private final UserService service;
    
    public UserResource(UserService service)
    {
        this.service = service;
    }
    
    @GET
    public Collection<User> retrieveAll()
    {
        return service.getAllUsers();
    }
    
    @GET
    @Path("/{id}")
    public User retrieve(@PathParam("id") int id)
    {
        return service.getUser(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(User user)
    {
        service.addUser(user);
    }
}

package nl.actorius.service;

import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.actorius.model.User;
import nl.actorius.persistence.UserDAO;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Peter van Vliet <peter@actorius.nl>
 */
public class AuthenticationServiceTest
{
    private final AuthenticationService subject;
    
    public AuthenticationServiceTest()
    {
        UserDAO userDAO = new UserDAO();
        
        subject = new AuthenticationService(userDAO);
    }
    
    @Before
    public void setUp()
    {
        
    }
    
    @After
    public void tearDown()
    {
        
    }
    
    @Test
    public void testAuthenticateSuccess() throws AuthenticationException
    {
        BasicCredentials credentials = new BasicCredentials("second@user.com", "second");
        
        Optional<User> actual = subject.authenticate(credentials);
        
        assertTrue(actual.isPresent());
    }
    
    @Test
    public void testAuthenticateWrongEmail() throws AuthenticationException
    {
        BasicCredentials credentials = new BasicCredentials("second@user.net", "second");
        
        Optional<User> actual = subject.authenticate(credentials);
        
        assertFalse(actual.isPresent());
    }
    
    @Test
    public void testAuthenticateWrongPassword() throws AuthenticationException
    {
        BasicCredentials credentials = new BasicCredentials("second@user.com", "first");
        
        Optional<User> actual = subject.authenticate(credentials);
        
        assertFalse(actual.isPresent());
    }
    
    @Test
    public void testAuthorizeSuccess()
    {
        User user = new User();
        user.setRoles(new String[] { "GUEST", "ADMIN" });
        
        boolean actual = subject.authorize(user, "ADMIN");
        
        assertTrue(actual);
    }
    
    @Test
    public void testAuthorizeFailed()
    {
        User user = new User();
        user.setRoles(new String[] { "GUEST" });
        
        boolean actual = subject.authorize(user, "ADMIN");
        
        assertFalse(actual);
    }
}

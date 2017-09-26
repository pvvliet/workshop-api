package nl.hsleiden.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import nl.hsleiden.model.User;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class UserDAO
{
    private final List<User> users;
    
    public UserDAO()
    {
        User user1 = new User();
        user1.setFullName("First user");
        user1.setPostcode("1234AB");
        user1.setStreetnumber("12");
        user1.setEmailAddress("first@user.com");
        user1.setPassword("first");
        user1.setRoles(new String[] { "GUEST", "ADMIN" });
        
        User user2 = new User();
        user2.setFullName("Second user");
        user2.setPostcode("9876ZY");
        user2.setStreetnumber("98");
        user2.setEmailAddress("second@user.com");
        user2.setPassword("second");
        user2.setRoles(new String[] { "GUEST" });
        
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }
    
    public List<User> getAll()
    {
        return users;
    }
    
    public User get(int id)
    {
        try
        {
            return users.get(id);
        }
        catch(IndexOutOfBoundsException exception)
        {
            return null;
        }
    }
    
    public User getByEmailAddress(String emailAddress)
    {
        Optional<User> result = users.stream()
            .filter(user -> user.getEmailAddress().equals(emailAddress))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public void add(User user)
    {
        users.add(user);
    }
    
    public void update(int id, User user)
    {
        users.set(id, user);
    }
    
    public void delete(int id)
    {
        users.remove(id);
    }
}

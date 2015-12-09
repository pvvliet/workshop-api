package nl.actorius.persistence;

import java.util.ArrayList;
import java.util.List;
import nl.actorius.model.User;

/**
 *
 * @author Peter van Vliet
 */
public class UserDAO
{
    private final List<User> users;
    
    public UserDAO()
    {
        User user1 = new User();
        user1.setFullName("First user");
        user1.setEmailAddress("first@user.com");
        user1.setPassword("first");
        
        User user2 = new User();
        user2.setFullName("Second user");
        user2.setEmailAddress("second@user.com");
        user2.setPassword("second");
        
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

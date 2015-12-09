package nl.actorius.service;

import java.util.Collection;
import nl.actorius.model.User;
import nl.actorius.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
public class UserService extends BaseService<User>
{
    private final UserDAO dao;
    
    public UserService(UserDAO dao)
    {
        this.dao = dao;
    }
    
    public User getUser(int id)
    {
        return requireResult(dao.getUser(id));
    }
    
    public Collection<User> getAllUsers()
    {
        return dao.getAllUsers();
    }
    
    public void addUser(User user)
    {
        dao.saveUser(user);
    }
}

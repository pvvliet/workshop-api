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
    
    public Collection<User> getAll()
    {
        return dao.getAll();
    }
    
    public User get(int id)
    {
        return requireResult(dao.get(id));
    }
    
    public void add(User user)
    {
        dao.add(user);
    }
    
    public void update(int id, User user)
    {
        // Eerst controleren of deze gebruiker wel bestaat
        User oldUser = get(id);
        
        dao.update(id, user);
    }
    
    public void delete(int id)
    {
        // Eerst controleren of deze gebruiker wel bestaat
        User user = get(id);
        
        dao.delete(id);
    }
}

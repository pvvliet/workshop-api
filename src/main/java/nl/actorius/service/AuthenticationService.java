/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.actorius.service;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.actorius.model.User;
import nl.actorius.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User>
{
    private final UserDAO userDAO;
    
    public AuthenticationService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        User user = userDAO.getByEmailAddress(credentials.getUsername());
        
        if (user != null && user.getPassword().equals(credentials.getPassword()))
        {
            return Optional.of(user);
        }
        
        return Optional.absent();
    }

    @Override
    public boolean authorize(User user, String roleName)
    {
        return user.hasRole(roleName);
    }
}

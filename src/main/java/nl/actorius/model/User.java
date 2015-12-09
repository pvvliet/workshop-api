package nl.actorius.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.actorius.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Peter van Vliet
 */
public class User
{
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String fullName;
    
    @Email
    @JsonView(View.Public.class)
    private String emailAddress;
    
    @Length(min = 8)
    @JsonView(View.Private.class)
    private String password;

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

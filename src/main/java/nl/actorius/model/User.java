package nl.actorius.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Peter van Vliet
 */
public class User
{
    @Length(min = 3, max = 100)
    @JsonProperty
    private String fullName;
    
    @Email
    @JsonProperty
    private String emailAddress;
    
    @Length(min = 8)
    @JsonProperty
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

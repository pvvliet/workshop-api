package nl.hsleiden.persistence;

import nl.hsleiden.model.User;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Peter van Vliet
 */
public class UserDAOTest
{
    private UserDAO subject;
    
    @Before
    public void setUp()
    {
        System.out.println("Set up");
        
        subject = new UserDAO();
    }
    
    @After
    public void tearDown()
    {
        System.out.println("Tear down");
        
        subject = null;
    }
    
    @Test
    public void testGetSuccess()
    {
        System.out.println("test get success");
        
        String expectedName = "Second user";
        User actual = subject.get(1);
        
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName());
    }
    
    @Test
    public void testGetFailed()
    {
        System.out.println("Test get failed");
        
        User actual = subject.get(6);
        
        assertNull(actual);
    }
    
    @Test
    public void testGetByEmailAddressSuccess()
    {
        System.out.println("Test by email address success");
        
        String expectedName = "First user";
        User actual = subject.getByEmailAddress("first@user.com");
        
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName());
    }
    
    @Test
    public void testGetByEmailAddressFailed()
    {
        System.out.println("Test by email address failed");
        
        User actual = subject.getByEmailAddress("third@user.com");
        
        assertNull(actual);
    }
    
    @Test
    public void testAdd()
    {
        System.out.println("Test add");
        
        User user = new User();
        user.setFullName("Peter van Vliet");
        
        subject.add(user);
        
        int expectedSize = 3;
        int actualSize = subject.getAll().size();
        
        assertEquals(expectedSize, actualSize);
    }
    
    @Test
    public void testUpdate()
    {
        User expected = new User();
        expected.setFullName("Peter van Vliet");
        
        subject.update(1, expected);
        
        User actual = subject.get(1);
        
        assertSame(expected, actual);
    }
    
    @Test
    public void testDelete()
    {
        subject.delete(1);
        
        User actual = subject.get(1);
        
        assertNull(actual);
    }
}

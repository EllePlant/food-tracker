package example.application;

import example.data.StaticUserDAO;
import example.data.User;
import example.data.UserDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests all methods in the data folder
 */
public class UserUnitTest {

    private UserDAO userDAO;
    private User user1;
    private User user2;


    @BeforeEach
    public void setUp() {
        user1 = new User("name1", "Pass1", "user1@email.com");
        user2 = new User("name2", "Pass2", "user2@email.com");
        userDAO = new StaticUserDAO();
    }

    @AfterEach
    public void tearDown() {
        // Remove all users from the database.
        for (User user : userDAO.listUsers()) {
            userDAO.deleteUser(user.getEmail());
        }
    }
    @Test
    public void testUserConstructor() {
        assertEquals( "name1", user1.getName(), "User's name not set");
        assertEquals("Pass1", user1.getPassword(), "User's password not set");
        assertEquals("user1@email.com", user1.getEmail(), "User's email not set");
    }

    @Test
    public void testUserSetter(){
        user1.setName("tempName");
        assertEquals("tempName", user1.getName(), "Could not change user's name");
    }

    @Test
    public void testAddUser(){
        userDAO.addUser(user1);
        assertEquals(1, userDAO.listUsers().size(), "Could not add user to the database");
    }

    @Test
    public void testGetUser() {
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        // Check that the user is found.
        assertEquals(user1, userDAO.getUser("user1@email.com"));
    }

    @Test
    public void testGetUserNotFound(){
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        // Check that the email3 is not found.
        assertNull(userDAO.getUser("user3@example.com"));
    }

    @Test
    public void testDeleteUser(){
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        userDAO.deleteUser("user1@email.com");
        // Check that the user was deleted, and that there is only one user left.
        assertNull(userDAO.getUser("user1@email.com"));
        assertEquals(1, userDAO.listUsers().size());
    }
    @Test
    public void testUpdateUser(){
        userDAO.addUser(user1);
        user1.setName("newName");
        userDAO.updateUser(user1);
        assertEquals(user1, userDAO.getUser(user1.getEmail()));
        assertEquals("newName", user1.getName());
    }
}


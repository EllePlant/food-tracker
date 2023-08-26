package example.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user of the application.
 */
public class User {
    private String name;
    private String password;
    private String email;

    private int userID;
    private PantryCollection pantry;

    /**
     * Creates a new User. Creates an empty pantry
     *
     * @param name     The name of the user.
     * @param password The password of the user.
     * @param email    The email of the user.
     */
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.pantry = new PantryCollection();
        this.userID++;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the userID of the user.
     *
     * @return The userID of the user.
     */
    public int getID() {
        return userID;
    }

    public PantryCollection getPantryCollection(){
        return pantry;
    }

    /**
     * Sets the name of the user.
     *
     * @param newName The new name of the user.
     */
    public void setName(String newName) {
       this.name = newName;
    }
}

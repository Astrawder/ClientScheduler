package com.company;

/**
 * Contact class containing all contact information in contact database table.
 *
 * @author Aidan Strawder
 */

public class Contact {
    private int ID;
    private String name;
    private String email;
    /**
     * Contact class constructor.
     *
     */
    public Contact(int ID, String name, String email){
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    //Getters
    /**
     * Return id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }
    /**
     * Return name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Return email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    //Setters
    /**
     * Set id.
     *
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

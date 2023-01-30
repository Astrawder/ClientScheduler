package com.company;

import java.sql.Timestamp;
/**
 * User class containing all user information in user database table.
 *
 * @author Aidan Strawder
 */
public class User {
    private int userID;
    private String username;
    private String password;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    /**
     * User class constructor.
     *
     */
    public User(int userID, String username, String password, Timestamp createDate,
                String createdBy, Timestamp lastUpdate, String lastUpdateBy){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    //Getters
    /**
     * Return id.
     *
     * @return the id
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Return the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Return the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Return the create date.
     *
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    /**
     * Return created by;
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Return last update.
     *
     * @return the lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Return last update by.
     *
     * @return the lastUpdateBy
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    //Setters
    /**
     * Set user id.
     *
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Set username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Set password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Set creation date.
     *
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    /**
     * Set created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Set last update.
     *
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * Set last update by.
     *
     * @param lastUpdateBy the lastUpdateBy to set
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}

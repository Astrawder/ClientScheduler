package com.company;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

/**
 * Appointment class containing all appointment information in appointment database table.
 *
 * @author Aidan Strawder
 */

public class Appointment {
    private int ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contact;
    private String startLocalDateTime;
    private String endLocalDateTime;
    /**
     * Appointment class constructor.
     *
     */
    public Appointment(int ID, String title, String description, String location,
                       String type, Timestamp startDateTime, Timestamp endDateTime,
                       Timestamp createDate, String createdBy, Timestamp lastUpdate,
                       String lastUpdateBy, int customerID, int userID, int contactID, String contact){
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contact = contact;
        this.startLocalDateTime = startDateTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
        this.endLocalDateTime = endDateTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));

    }
    // Getters
    /**
     * Return id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }
    /**
     * Return title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Return description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Return location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    /**
     * Return type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * Return start date/time.
     *
     * @return the start timestamp
     */
    public Timestamp getStartDateTime() {
        return startDateTime;
    }
    /**
     * Return end date/time.
     *
     * @return the end timestamp
     */
    public Timestamp getEndDateTime() {
        return endDateTime;
    }
    /**
     * Return create date.
     *
     * @return the date of creation
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    /**
     * Return created by.
     *
     * @return the name of user who created
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Return last update.
     *
     * @return the last update timestamp
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Return last updated by.
     *
     * @return the user who lasted updated
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }
    /**
     * Return customer id.
     *
     * @return the customer id
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * Return user id.
     *
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Return contact id.
     *
     * @return the contact id
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * Return contact name.
     *
     * @return the contact name
     */

    public String getContact() {
        return contact;
    }
    /**
     * Return local start date/time.
     *
     * @return the starting LocalDateTime
     */
    public String getStartLocalDateTime() {
        return startLocalDateTime;
    }
    /**
     * Return local end date/time.
     *
     * @return the ending LocalDateTime
     */
    public String getEndLocalDateTime() {
        return endLocalDateTime;
    }

    // Setters
    /**
     * Set id.
     *
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Set title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Set description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Set location.
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Set type.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Set start date/time.
     *
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }
    /**
     * Set end date/time.
     *
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }
    /**
     * Set create date.
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
    /**
     * Set customer id.
     *
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     * Set user id.
     *
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Set contact id.
     *
     * @param contactID the contactID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * Set contact.
     *
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    /**
     * Set local start date/time.
     *
     * @param startLocalDateTime the startLocalDateTime to set
     */
    public void setStartLocalDateTime(String startLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
    }
    /**
     * Set local end date/time.
     *
     * @param endLocalDateTime the endLocalDateTime to set
     */
    public void setEndLocalDateTime(String endLocalDateTime) {
        this.endLocalDateTime = endLocalDateTime;
    }
}

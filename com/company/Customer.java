package com.company;

import java.sql.Timestamp;

/**
 * Customer class containing all customer information in customer database table.
 *
 * @author Aidan Strawder
 */

public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private int divisionID;
    private String country;
    private String division;
    /**
     * Country class constructor.
     * Includes division id and country attributes.
     *
     */
    public Customer(int id, String name, String address, String postalCode, String phone,
                    Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy,
                    int divisionID, String division, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.divisionID = divisionID;
        this.division = division;
        this.country = country;
    }
    /**
     * Country class constructor.
     *
     */
    public Customer(int id, String name, String address, String postalCode, String phone,
                    Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy,
                    int divisionID){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.divisionID = divisionID;
    }
    // Getters
    /**
     * Return id.
     *
     * @return the id
     */
    public int getID() {
        return id;
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
     * Return address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Return postal code.
     *
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Return phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Return create date.
     *
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    /**
     * Return created by.
     *
     * @return the createBy
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
    /**
     * Return division id.
     *
     * @return the divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * Return country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * Return division.
     *
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    //Setters
    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setID(int id) {
        this.id = id;
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
     * Set address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Set postal code.
     *
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * Set phone number.
     *
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Set last update by
     *
     * @param lastUpdateBy the lastUpdateBy to set
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    /**
     * Set last updated by.
     *
     * @param divisionID the divisionID to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**
     * Set country.
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * Set division.
     *
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }
}

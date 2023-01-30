package com.company;

import java.sql.Timestamp;

/**
 * FirstLevelDivision class containing all division information in firstleveldivision database table.
 *
 * @author Aidan Strawder
 */

public class FirstLevelDivision {
    private int id;
    private String division;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    private int countryID;
    /**
     * FirstLevelDivision class constructor.
     *
     */
    public FirstLevelDivision(int id, String division, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int countryID){
        this.id = id;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.countryID = countryID;
    }
    //Getters
    /**
     * Return id.
     *
     * @return the id
     */
    public int getID() {
        return id;
    }
    /**
     * Return division.
     *
     * @return the division
     */
    public String getDivision() {
        return division;
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
    /**
     * Return country id.
     *
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
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
     * Set division.
     *
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
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
     * Set country id.
     *
     * @param countryID the countryID to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}

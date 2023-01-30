package com.company;

import java.sql.Timestamp;

/**
 * Country class containing all country information in country database table.
 *
 * @author Aidan Strawder
 */

public class Country {
    private int id;
    private String country;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdateBy;
    /**
     * Country class constructor.
     *
     */
    public Country(int id, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy){
        this.id = id;
        this.country = country;
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
    public int getID() {
        return id;
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
     * Return create date.
     *
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    /**
     * return created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Return last update.
     *
     * @return the lastUpdate;
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
     * Set id.
     *
     * @param id the id to set
     */
    public void setID(int id) {
        this.id = id;
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
     * @param createdBy the createBy to set
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

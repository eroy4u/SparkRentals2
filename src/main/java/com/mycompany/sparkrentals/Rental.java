/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author eroy4u
 */
public class Rental {

    @Field
    private String id;

    @Field
    private String city;

    @Field
    private String province;

    @Field
    private String country;

    @Field
    private String zipCode;

    @Field
    private String type;

    @Field
    private boolean hasAirCondition;

    @Field
    private boolean hasGarden;

    @Field
    private boolean hasPool;

    @Field
    private boolean isCloseToBeach;

    @Field
    private float dailyPrice;

    @Field
    private String currency;

    @Field
    private int roomsNumber;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the hasAirCondition
     */
    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    /**
     * @param hasAirCondition the hasAirCondition to set
     */
    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    /**
     * @return the hasGarden
     */
    public boolean isHasGarden() {
        return hasGarden;
    }

    /**
     * @param hasGarden the hasGarden to set
     */
    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    /**
     * @return the hasPool
     */
    public boolean isHasPool() {
        return hasPool;
    }

    /**
     * @param hasPool the hasPool to set
     */
    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    /**
     * @return the isCloseToBeach
     */
    public boolean isIsCloseToBeach() {
        return isCloseToBeach;
    }

    /**
     * @param isCloseToBeach the isCloseToBeach to set
     */
    public void setIsCloseToBeach(boolean isCloseToBeach) {
        this.isCloseToBeach = isCloseToBeach;
    }

    /**
     * @return the dailyPrice
     */
    public float getDailyPrice() {
        return dailyPrice;
    }

    /**
     * @param dailyPrice the dailyPrice to set
     */
    public void setDailyPrice(float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the roomsNumber
     */
    public int getRoomsNumber() {
        return roomsNumber;
    }

    /**
     * @param roomsNumber the roomsNumber to set
     */
    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

}

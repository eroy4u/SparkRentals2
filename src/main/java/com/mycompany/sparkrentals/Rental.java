/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import java.util.Arrays;
import java.util.Map;
import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author eroy4u
 */
public class Rental {
    
    public Rental(){
    }
    
    /**
     * Retrieve from a map with its key is the field  name
     * its value is the field value
     * @param map 
     */
    public void SetValuesFromMap(Map<String,Object> map){
        if (map.containsKey("id")){
            this.id = (String) map.get("id");
        }
        if (map.containsKey("city")){
            this.city = (String) map.get("city");
        }
        if (map.containsKey("province")){
            this.province = (String) map.get("province");
        }
        if (map.containsKey("country")){
            this.country = (String) map.get("country");
        }
        if (map.containsKey("zipCode")) {
            this.zipCode = (String) map.get("zipCode");
        }
        if (map.containsKey("type")) {
            this.type = (String) map.get("type");
        }
        if (map.containsKey("hasAirCondition")) {
            this.hasAirCondition = "Yes".equals((String) map.get("hasAirCondition"));
        }
        if (map.containsKey("hasGarden")) {
            this.hasGarden = "Yes".equals((String) map.get("hasGarden"));
        }
        if (map.containsKey("hasPool")) {
            this.hasPool = "Yes".equals((String) map.get("hasPool"));
        }
        if (map.containsKey("isCloseToBeach")) {
            this.isCloseToBeach = "Yes".equals((String) map.get("isCloseToBeach"));
        }
        if (map.containsKey("dailyPrice")) {
            this.dailyPrice = (float) map.get("dailyPrice");
        }
        if (map.containsKey("currency")) {
            this.currency = (String) map.get("currency");
        }
        if (map.containsKey("roomsNumber")) {
            this.roomsNumber = (int) map.get("roomsNumber");
        }
    }

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

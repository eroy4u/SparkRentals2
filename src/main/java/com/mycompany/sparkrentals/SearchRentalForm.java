/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.QueryParamsMap;

/**
 * Validates search input and return error messages if there are
 *
 * @author eroy4u
 */
public class SearchRentalForm {

    private final QueryParamsMap map;

    public SearchRentalForm(QueryParamsMap map) {
        this.map = map;
    }

    private List<String> errorMessages = new ArrayList<>();

    public List<String> getErrorMessages() {
        return errorMessages;
    }
    private Map<String, Object> cleanedData = new HashMap<>();
    private Map<String, Object> dataToDisplay = new HashMap<>();

    /**
     * Validate all fields
     * If there are errors, return false, and you can call getErrorMessages()
     * to see the errors and call getDataToDisplay() to display the data
     * in the submission form
     * Otherwise, call getCleanedData to get the cleaned data
     * 
     * @return 
     */
    public boolean validate() {
        if (map.get("city") != null) {
            validateChoices("city", map.get("city").value(),
                    SelectionOptions.getCityOptions());
        }
        if (map.get("province") != null) {
            validateChoices("province", map.get("province").value(),
                    SelectionOptions.getProvinceOptions());
        }
        if (map.get("type") != null) {
            validateChoices("type", map.get("type").value(),
                    SelectionOptions.getTypeOptions());
        }
        if (map.get("country") != null) {
            validateChoices("country", map.get("country").value(),
                    SelectionOptions.getCountryOptions());
        }
        for (String field: Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")){
            if (map.get(field) != null) {
                validateChoices(field, map.get(field).value(),
                        SelectionOptions.getYesNoOptions());
            }
        }
        for (String field: Arrays.asList("roomsNumberFrom", "roomsNumberTo")){
            if (map.get(field) != null) {
                validateInt(field, map.get(field).value());
            }
        }
        for (String field: Arrays.asList("dailyPriceFrom", "dailyPriceTo")){
            if (map.get(field) != null) {
                validateFloat(field, map.get(field).value());
            }
        }
        
        if (map.get("zipCode") != null){
            validateMaxLength("zipCode", map.get("zipCode").value(), 5);
        }

        return true;

    }
    private void validateMaxLength(String field, String value, int maxLength){
        if (value == null || value.isEmpty()){
            return;
        }
        if (value.length() > maxLength){
            errorMessages.add(field + " should not contains more than "+maxLength+" characters");
        }else{
            cleanedData.put(field, value);
        }
        dataToDisplay.put(field, value);
    }
    private void validateFloat(String field, String value){
        if (value == null || value.isEmpty()){
            return;
        }
        try{
            float parsedValue = Float.parseFloat(value);
            cleanedData.put(field, parsedValue);
        }catch(NumberFormatException e){
            errorMessages.add(field + " doesn't cotain a valid number.");
        }
        dataToDisplay.put(field, value);

    }
    private void validateInt(String field, String value){
        if (value == null || value.isEmpty()){
            return;
        }
        try{
            int parsedValue = Integer.parseInt(value);
            cleanedData.put(field, parsedValue);
        }catch(NumberFormatException e){
            errorMessages.add(field + " doesn't cotain a valid number.");
        }
        dataToDisplay.put(field, value);
    }
    
    private void validateChoices(String field, String value, List<String> allowedChoices) {
        if (value == null || value.isEmpty()) {
            return;
        }
        boolean matched = false;
        for (String eachAllowedChoice : allowedChoices) {
            if (eachAllowedChoice.equals(value)) {
                matched = true;
            }
        }
        if (matched) {
            cleanedData.put(field, value);
        }else{
            errorMessages.add(field + " contains invalid choice.");
        }
        dataToDisplay.put(field, value);
    }

    /**
     * @return the cleanedData
     */
    public Map<String, Object> getCleanedData() {
        return cleanedData;
    }

    /**
     * @return the dataToDisplay
     */
    public Map<String, Object> getDataToDisplay() {
        return dataToDisplay;
    }
}

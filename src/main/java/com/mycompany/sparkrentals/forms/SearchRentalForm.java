/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.forms;

import com.mycompany.sparkrentals.SelectionOptions;
import java.util.Arrays;

/**
 * Validates search input and return error messages if there are
 *
 * @author eroy4u
 */
public class SearchRentalForm extends BaseForm {


    public SearchRentalForm() {
    }


    /**
     * Validate all fields
     * If there are errors, return false, and you can call getErrorMessages()
     * to see the errors and call getDataToDisplay() to display the data
     * in the submission form
     * Otherwise, call getCleanedData to get the cleaned data
     * 
     * @return 
     */
    @Override
    public boolean validate() {
        if (dataMap.get("city") != null) {
            validateChoices("city", dataMap.get("city").value(),
                    SelectionOptions.getCityOptions());
        }
        if (dataMap.get("province") != null) {
            validateChoices("province", dataMap.get("province").value(),
                    SelectionOptions.getProvinceOptions());
        }
        if (dataMap.get("type") != null) {
            validateChoices("type", dataMap.get("type").value(),
                    SelectionOptions.getTypeOptions());
        }
        if (dataMap.get("country") != null) {
            validateChoices("country", dataMap.get("country").value(),
                    SelectionOptions.getCountryOptions());
        }
        for (String field: Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")){
            if (dataMap.get(field) != null) {
                validateChoices(field, dataMap.get(field).value(),
                        SelectionOptions.getYesNoOptions());
            }
        }
        for (String field: Arrays.asList("roomsNumberFrom", "roomsNumberTo", "timePeriod")){
            if (dataMap.get(field) != null) {
                validateNonNegativeInt(field, dataMap.get(field).value());
            }
        }
        for (String field: Arrays.asList("dailyPriceFrom", "dailyPriceTo")){
            if (dataMap.get(field) != null) {
                validateNonNegativeFloat(field, dataMap.get(field).value());
            }
        }
        
        if (dataMap.get("zipCode") != null){
            validateAlphaNumericLength("zipCode", dataMap.get("zipCode").value(), 5);
        }
        
        return errorMessages.size() <= 0;

    }
}
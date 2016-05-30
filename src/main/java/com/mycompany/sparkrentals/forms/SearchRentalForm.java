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
        if (queryMap.get("city") != null) {
            validateChoices("city", queryMap.get("city").value(),
                    SelectionOptions.getCityOptions());
        }
        if (queryMap.get("province") != null) {
            validateChoices("province", queryMap.get("province").value(),
                    SelectionOptions.getProvinceOptions());
        }
        if (queryMap.get("type") != null) {
            validateChoices("type", queryMap.get("type").value(),
                    SelectionOptions.getTypeOptions());
        }
        if (queryMap.get("country") != null) {
            validateChoices("country", queryMap.get("country").value(),
                    SelectionOptions.getCountryOptions());
        }
        for (String field: Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")){
            if (queryMap.get(field) != null) {
                validateChoices(field, queryMap.get(field).value(),
                        SelectionOptions.getYesNoOptions());
            }
        }
        for (String field: Arrays.asList("roomsNumberFrom", "roomsNumberTo", "timePeriod")){
            if (queryMap.get(field) != null) {
                validateNonNegativeInt(field, queryMap.get(field).value());
            }
        }
        for (String field: Arrays.asList("dailyPriceFrom", "dailyPriceTo")){
            if (queryMap.get(field) != null) {
                validateNonNegativeFloat(field, queryMap.get(field).value());
            }
        }
        
        if (queryMap.get("zipCode") != null){
            validateAlphaNumericLength("zipCode", queryMap.get("zipCode").value(), 5);
        }
        
        //validate page number
        if (queryMap.get("page") != null){
            validateNonNegativeInt("page", queryMap.get("page").value());
        }
        return errorMessages.size() <= 0;

    }
}

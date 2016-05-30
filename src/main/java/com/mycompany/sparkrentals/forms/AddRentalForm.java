/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.forms;

import com.mycompany.sparkrentals.SelectionOptions;
import java.util.Arrays;
import spark.utils.StringUtils;

/**
 * Validates add/update of rental information
 *
 * @author eroy4u
 */
public class AddRentalForm extends BaseForm {

    /**
     * Validate all fields If there are errors, return false, and you can call
     * getErrorMessages() to see the errors and call getDataToDisplay() to
     * display the data in the submission form Otherwise, call getCleanedData to
     * get the cleaned data
     *
     * @return
     */
    @Override
    public boolean validate() {

        //checking if every field has been filled in
        for (String field : Arrays.asList("id", "city", "province", "type",
                "country", "hasAirCondition", "hasGarden", "hasPool",
                "isCloseToBeach", "roomsNumber", "dailyPrice", "zipCode")) {
            if (queryMap.get(field) == null || StringUtils.isEmpty(queryMap.get(field))) {
                errorMessages.add(field + " is required.");
            }
        }

        //validate every field
        if (queryMap.get("city") != null) {
            validateChoices("city", queryMap.get("city"),
                    SelectionOptions.getCityOptions());
        }
        if (queryMap.get("province") != null) {
            validateChoices("province", queryMap.get("province"),
                    SelectionOptions.getProvinceOptions());
        }
        if (queryMap.get("type") != null) {
            validateChoices("type", queryMap.get("type"),
                    SelectionOptions.getTypeOptions());
        }
        if (queryMap.get("country") != null) {
            validateChoices("country", queryMap.get("country"),
                    SelectionOptions.getCountryOptions());
        }
        for (String field : Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")) {
            if (queryMap.get(field) != null) {
                validateChoices(field, queryMap.get(field),
                        SelectionOptions.getYesNoOptions());
            }
        }
        if (queryMap.get("roomsNumber") != null) {
            validateNonNegativeInt("roomsNumber", queryMap.get("roomsNumber"));
        }

        if (queryMap.get("dailyPrice") != null) {
            validateNonNegativeFloat("dailyPrice", queryMap.get("dailyPrice"));
        }

        if (queryMap.get("id") != null) {
            validateAlphaNumericLength("id", queryMap.get("id"), 5);
        }
        if (queryMap.get("zipCode") != null) {
            validateAlphaNumericLength("zipCode", queryMap.get("zipCode"), 5);
        }

        return errorMessages.size() <= 0;

    }
}

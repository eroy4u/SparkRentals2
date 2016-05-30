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
            if (dataMap.get(field) == null || StringUtils.isEmpty(dataMap.get(field).value())) {
                errorMessages.add(field + " is required.");
            }
        }

        //validate every field
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
        for (String field : Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")) {
            if (dataMap.get(field) != null) {
                validateChoices(field, dataMap.get(field).value(),
                        SelectionOptions.getYesNoOptions());
            }
        }
        if (dataMap.get("roomsNumber") != null) {
            validateNonNegativeInt("roomsNumber", dataMap.get("roomsNumber").value());
        }

        if (dataMap.get("dailyPrice") != null) {
            validateNonNegativeFloat("dailyPrice", dataMap.get("dailyPrice").value());
        }

        if (dataMap.get("id") != null) {
            validateAlphaNumericLength("id", dataMap.get("id").value(), 5);
        }
        if (dataMap.get("zipCode") != null) {
            validateAlphaNumericLength("zipCode", dataMap.get("zipCode").value(), 5);
        }

        return errorMessages.size() <= 0;

    }
}

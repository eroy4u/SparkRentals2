/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import spark.QueryParamsMap;

/**
 *
 * @author eroy4u
 */
public abstract class BaseForm {

    protected Map<String, String> queryMap;
    protected List<String> errorMessages = new ArrayList<>();
    protected Map<String, Object> cleanedData = new HashMap<>();
    protected Map<String, Object> dataToDisplay = new HashMap<>();

    public BaseForm() {
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void clear() {
        errorMessages.clear();
        cleanedData.clear();
        dataToDisplay.clear();
    }

    /**
     * Validate all fields If there are errors, return false, and you can call
     * getErrorMessages() to see the errors and call getDataToDisplay() to
     * display the data in the submission form Otherwise, call getCleanedData to
     * get the cleaned data
     *
     * @return
     */
    public abstract boolean validate();

    public void validateAlphaNumericLength(String field, String value, int maxLength) {
        if (value == null || value.isEmpty()) {
            return;
        }
        if (value.length() != maxLength || !StringUtils.isAlphanumeric(value)) {
            errorMessages.add(field + " should should contains " + maxLength + " alphanumeric characters");
        } else {
            cleanedData.put(field, value);
        }
        dataToDisplay.put(field, value);
    }

    public void validateNonNegativeFloat(String field, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            float parsedFloat = Float.parseFloat(value);
            if (parsedFloat < 0) {
                errorMessages.add(field + " should be non-negative.");
            } else {
                cleanedData.put(field, parsedFloat);
            }
        } catch (NumberFormatException e) {
            errorMessages.add(field + " doesn't cotain a valid number.");
        }
        dataToDisplay.put(field, value);
    }

    public void validateNonNegativeInt(String field, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            int parsedInteger = Integer.parseInt(value);
            if (parsedInteger < 0) {
                errorMessages.add(field + " should be non-negative.");
            } else {
                cleanedData.put(field, parsedInteger);
            }
        } catch (NumberFormatException e) {
            errorMessages.add(field + " doesn't cotain a valid number.");
        }
        dataToDisplay.put(field, value);
    }

    public void validateChoices(String field, String value, List<String> allowedChoices) {
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
        } else {
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

    /**
     * @param queryMap the map to set
     */
    public void setQueryMap(QueryParamsMap queryParamsMap) {
        queryMap = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : queryParamsMap.toMap().entrySet()) {
            if (entry.getValue().length > 0) {
                queryMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }

    }

}

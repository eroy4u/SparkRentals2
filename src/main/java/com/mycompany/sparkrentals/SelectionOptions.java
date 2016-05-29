/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author eroy4u
 */
public final class SelectionOptions {

    private SelectionOptions() {
        //private constructor
    }
    /**
     * Return allowed choices for city from city0 to city98
     * It's for demo purpose
     * @return 
     */
    public static List<String> getCityOptions() {
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i <= 98; i++) {
            cityList.add("city" + i);
        }
        return cityList;
    }

    /**
     * Return allowed province choices from city0 to city98
     * @return 
     */
    public static List<String> getProvinceOptions() {
        List<String> provinceList = new ArrayList<>();
        for (int i = 0; i <= 98; i++) {
            provinceList.add("province" + i);
        }
        return provinceList;
    }

    /**
     * Returns country choices that's allowed
     * @return 
     */
    public static List<String> getCountryOptions() {
        return Arrays.asList("China", "country", "Egypt",
                    "France", "India", "Russia", "South Africa", "Thailand",
                    "UK");
    }
    
    /**
     * return allowed choices for rental type
     * @return 
     */
    public static List<String> getTypeOptions(){
        return Arrays.asList("bungalow", "Deluxe", "Single Floor",
                "studio", "Villa");
    }
    /**
     * return allowed choices for Yes/No
     * @return 
     */
    public static List<String> getYesNoOptions(){
        return Arrays.asList("Yes", "No");
    }
}

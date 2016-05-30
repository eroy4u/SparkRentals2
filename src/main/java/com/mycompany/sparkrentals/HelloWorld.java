/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import com.mycompany.sparkrentals.forms.AddRentalForm;
import com.mycompany.sparkrentals.forms.SearchRentalForm;
import freemarker.template.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrException;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.staticFileLocation;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.post;
import java.util.Date;

/**
 *
 * @author eroy4u
 */
public class HelloWorld {

    public static void main(String[] args) {
        // Configure the view directory
        Configuration viewConfig = new Configuration();
        viewConfig.setClassForTemplateLoading(HelloWorld.class, "/views");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(viewConfig);

        // Configure the static files directory
        staticFileLocation("/public");

        // Configure Solr connection
        String url = "http://localhost:8983/solr/new_core";
        SolrClient client = new HttpSolrClient(url);

        // Configure Cassandra connection
        String cqlHost = "127.0.0.1";
        CassandraClient cqlClient = new CassandraClient();
        cqlClient.connect(cqlHost);

        get("/add", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("data", new HashMap<>());
            fillFormSelectionOption(attributes);

            return new ModelAndView(attributes, "add.ftl");

        }, freeMarkerEngine);

        post("/add", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            AddRentalForm form = new AddRentalForm();
            form.setQueryMap(request.queryMap());
            if (form.validate()) {

                //valid rental data, so will insert into database
                // or it'll update if one recrod already exists with the same key
                Rental rental = new Rental();
                rental.SetValuesFromMap(form.getCleanedData());
                //Assuming for now there's only one currency $
                //so we don't need to ask the user to select, we preset it here
                rental.setCurrency("$");
                rental.setUpdated(new Date());
                try{
                    cqlClient.insertOrUpdateRental(rental);
                }catch (Exception e){
                    throw e;
                }
                
                client.addBean(rental);
                client.commit();
                

                return new ModelAndView(attributes, "add_done.ftl");

            }
            // form contains errors

            attributes.put("errorMessages", form.getErrorMessages());
            attributes.put("data", form.getDataToDisplay());
            fillFormSelectionOption(attributes);

            return new ModelAndView(attributes, "add.ftl");

        }, freeMarkerEngine);

        get("/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            SearchRentalForm form = new SearchRentalForm();
            form.setQueryMap(request.queryMap());
            
            int perPage = 20;
            if (form.validate()) {
                Map<String, Object> cleanedData = form.getCleanedData();
                SolrQuery query = new SolrQuery("*");

                for (String field : Arrays.asList("city", "province", "country", "type", "zipCode")) {
                    if (cleanedData.get(field) != null) {
                        query.addFilterQuery(field + ":\"" + cleanedData.get(field) + "\"");
                    }
                }
                for (String field : Arrays.asList("hasAirCondition", "hasGarden", "hasPool", "isCloseToBeach")) {
                    if (cleanedData.get(field) != null) {
                        boolean isYes = cleanedData.get(field).equals("Yes");
                        query.addFilterQuery(field + ":\"" + isYes + "\"");
                    }
                }

                if (cleanedData.get("roomsNumberFrom") != null || cleanedData.get("roomsNumberTo") != null) {
                    String roomsNumberFrom = "*";
                    String roomsNumberTo = "*";
                    if (cleanedData.get("roomsNumberFrom") != null) {
                        roomsNumberFrom = String.format("%.2f",
                                (float) cleanedData.get("roomsNumberFrom"));
                    }
                    if (cleanedData.get("roomsNumberTo") != null) {
                        roomsNumberTo = String.format("%.2f",
                                (float) cleanedData.get("roomsNumberTo"));
                    }
                    String filterString = "roomsNumber:[" + roomsNumberFrom + " TO " + roomsNumberTo + "]";
                    query.addFilterQuery(filterString);
                }
                if (cleanedData.get("dailyPriceFrom") != null || cleanedData.get("dailyPriceTo") != null) {
                    String dailyPriceFrom = "*";
                    String dailyPriceTo = "*";
                    if (cleanedData.get("dailyPriceFrom") != null) {
                        dailyPriceFrom = String.format("%.2f",
                                (float) cleanedData.get("dailyPriceFrom"));
                    }
                    if (cleanedData.get("dailyPriceTO") != null) {
                        dailyPriceTo = String.format("%.2f",
                                (float) cleanedData.get("dailyPriceTO"));
                    }
                    String filterString = "dailyPrice:[" + dailyPriceFrom + " TO " + dailyPriceTo + "]";
                    query.addFilterQuery(filterString);
                }
                if (cleanedData.get("timePeriod") != null){
                    int timePeriod = (int)cleanedData.get("timePeriod");
                    query.addFilterQuery("updated:[NOW-"+timePeriod+"DAY TO * ]");
                }
                
                int currentPage = (int) cleanedData.getOrDefault("page", 1);
                query.setStart((currentPage-1)*perPage);
                query.setRows(perPage);
                
                try {
                    QueryResponse queryResponse = client.query(query);
                    List<Rental> rentalList = queryResponse.getBeans(Rental.class);
                    
                    long resultsTotal = queryResponse.getResults().getNumFound();
                    attributes.put("resultsTotal", resultsTotal);
                    attributes.put("currentPage", currentPage);
                    long maxPage = (resultsTotal % perPage > 0 ? 1: 0) + resultsTotal / perPage;
                    attributes.put("maxPage", maxPage);
                    
                    attributes.put("rentalList", rentalList);
                    attributes.put("errorMessages", new ArrayList<>());

                } catch (SolrException e) {
                    //there is an error for querying
                    attributes.put("rentalList", new ArrayList<>());
                    attributes.put("errorMessages", Arrays.asList("Solr query error!"));
                }

            } else {
                attributes.put("rentalList", new ArrayList<>());
                attributes.put("errorMessages", form.getErrorMessages());
            }

            attributes.put("data", form.getDataToDisplay());

            fillFormSelectionOption(attributes);
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);
    }

    private static void fillFormSelectionOption(Map<String, Object> attributes) {
        attributes.put("countryOptions", SelectionOptions.getCountryOptions());
        attributes.put("provinceOptions", SelectionOptions.getProvinceOptions());
        attributes.put("cityOptions", SelectionOptions.getCityOptions());
        attributes.put("typeOptions", SelectionOptions.getTypeOptions());
        attributes.put("yesNoOptions", SelectionOptions.getYesNoOptions());

    }
}

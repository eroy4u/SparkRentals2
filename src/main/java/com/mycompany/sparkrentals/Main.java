/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import com.datastax.driver.core.exceptions.DriverException;
import com.mycompany.sparkrentals.forms.AddRentalForm;
import com.mycompany.sparkrentals.forms.SearchRentalForm;
import freemarker.template.Configuration;
import java.io.IOException;
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
import java.util.Date;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.solr.client.solrj.SolrServerException;
import static spark.Spark.exception;
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

/**
 *
 * @author eroy4u
 */
public class Main {
    
    private static String solrUrl;
    private static String cqlHost;
    private static String cqlKeyspace;
    
    /**
     * This program can be run with arguments
     * -solr [solr url] -cql [cassandra url] -keyspace [keyspace name]
     * this will override the default settings
     * @param args 
     */
    private static void initializeSolrCqlHostsFromArgs(String[] args){
        solrUrl = "http://localhost:8983/solr/new_core";
        cqlHost = "127.0.0.1";
        cqlKeyspace = "rentalskeyspace";

        Options options = new Options();
        options.addOption("solr", true, "Solr host url");
        options.addOption("cql", true, "cassandra host address");
        options.addOption("keyspace", true, "cassandra keyspace name");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("run with arguments -solr [solr url] -cql [cassandra url] -keyspace [keyspace name]");
            return;
        }
        if (cmd.hasOption("solr")) {
            solrUrl = cmd.getOptionValue("solr");
            System.out.println("SolrUrl = " + solrUrl);
        }
        if (cmd.hasOption("cql")) {
            cqlHost = cmd.getOptionValue("cql");
        }
        if (cmd.hasOption("keyspace")) {
            cqlKeyspace = cmd.getOptionValue("keyspace");
        }
    }

    public static void main(String[] args) {
        
        initializeSolrCqlHostsFromArgs(args);
        
        // Configure Solr connection
        RentalSolrClient solrClient = new RentalSolrClient();
        solrClient.connect(solrUrl);

        // Configure Cassandra connection
        CqlClient cqlClient = new CqlClient();
        cqlClient.connect(cqlHost, cqlKeyspace);
        
        // Configure the view directory
        Configuration viewConfig = new Configuration();
        viewConfig.setClassForTemplateLoading(Main.class, "/views");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(viewConfig);

        // Configure the static files directory
        staticFileLocation("/public");
        
        //exception handling
        exception(DriverException.class, (exception, request, response) -> {
            //handle exception for cassandra serve exception
            response.body("Something wrong for cassandra server "+exception.getMessage());
        });
        exception(Exception.class, (exception, request, response) -> {
            //handle exception
            response.body("Sorry something went wrong. Please try again later.");
        });
        
        //start setting up routes here
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

                //valid rental data, so will insert into database and solr
                // or it'll update if one recrod already exists with the same key
                Rental rental = new Rental();
                rental.SetValuesFromMap(form.getCleanedData());
                //Assuming for now there's only one currency $
                //so we don't need to ask the user to select, we preset it here
                rental.setCurrency("$");
                rental.setUpdated(new Date());

                //insert into cql db
                cqlClient.insertOrUpdateRental(rental);
                
                //add index to solr at the same time
                try{
                    solrClient.addRental(rental);
                }catch(IOException e){
                    attributes.put("message", "exception connecting to solr server");
                    return new ModelAndView(attributes, "exception.ftl");
                }catch(SolrServerException e){
                    attributes.put("message", "solr server exception");
                    return new ModelAndView(attributes, "exception.ftl");
                }

                return new ModelAndView(attributes, "add_done.ftl");

            }
            // form contains errors
            attributes.put("errorMessages", form.getErrorMessages());
            attributes.put("data", form.getDataToDisplay());
            fillFormSelectionOption(attributes);

            return new ModelAndView(attributes, "add.ftl");

        }, freeMarkerEngine);

        //index is the search page
        get("/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            SearchRentalForm form = new SearchRentalForm();
            form.setQueryMap(request.queryMap());
            
            int perPage = 20; //number of results per page
            if (form.validate()) {
                Map<String, Object> cleanedData = form.getCleanedData();
                
                try {
                    QueryResponse queryResponse = solrClient.searchRentals(cleanedData, perPage);
                    List<Rental> rentalList = queryResponse.getBeans(Rental.class);
                    
                    //these are for pagination purpose
                    long resultsTotal = queryResponse.getResults().getNumFound();
                    attributes.put("resultsTotal", resultsTotal);
                    int currentPage = (int) cleanedData.getOrDefault("page", 1);
                    attributes.put("currentPage", currentPage);
                    long maxPage = (resultsTotal % perPage > 0 ? 1: 0) + resultsTotal / perPage;
                    attributes.put("maxPage", maxPage);
                    
                    attributes.put("rentalList", rentalList);
                    attributes.put("errorMessages", new ArrayList<>());

                } catch (SolrException e) {
                    //there is an error for querying
                    attributes.put("errorMessages", Arrays.asList("Solr query error!"));
                } catch (Exception e){
                    attributes.put("errorMessages", Arrays.asList("Exception when connecting to Solr!"+e.getMessage()));
                }

            } else {
                //search form not valid
                attributes.put("rentalList", new ArrayList<>());
                attributes.put("errorMessages", form.getErrorMessages());
            }
            if (!attributes.containsKey("rentalList")){
                attributes.put("rentalList", new ArrayList<>());
            }
            //for disply back to user entered data
            attributes.put("data", form.getDataToDisplay());

            fillFormSelectionOption(attributes);
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);
    }
    /**
     * Fill countryOptions, provinceOptions, cityOptions, typeOptions, yesNoOptions
     * @param attributes 
     */
    private static void fillFormSelectionOption(Map<String, Object> attributes) {
        attributes.put("countryOptions", SelectionOptions.getCountryOptions());
        attributes.put("provinceOptions", SelectionOptions.getProvinceOptions());
        attributes.put("cityOptions", SelectionOptions.getCityOptions());
        attributes.put("typeOptions", SelectionOptions.getTypeOptions());
        attributes.put("yesNoOptions", SelectionOptions.getYesNoOptions());

    }
}

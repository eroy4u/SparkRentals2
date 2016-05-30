/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 *
 * @author eroy4u
 */
public class RentalSolrClient {
    
    public SolrClient solrClient = null;
    public void connect(String solrUrl) {
        solrClient = new HttpSolrClient(solrUrl);
    }
    /**
     * add rental object to solr index
     * @param rental
     * @throws IOException
     * @throws SolrServerException 
     */
    public void addRental(Rental rental) throws IOException, SolrServerException{
        solrClient.addBean(rental);
        solrClient.commit();
    }
    
    /**
     * search results based on field values in cleanedData
     * @param cleanedData
     * @param perPage
     * @return
     * @throws SolrServerException
     * @throws IOException 
     */
    public QueryResponse searchRentals(Map<String, Object> cleanedData, int perPage)
            throws SolrServerException, IOException{
        SolrQuery query = new SolrQuery("*");
        
        //start adding filtering
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
        if (cleanedData.get("timePeriod") != null) {
            int timePeriod = (int) cleanedData.get("timePeriod");
            query.addFilterQuery("updated:[NOW-" + timePeriod + "DAY TO * ]");
        }

        //pagination
        int currentPage = (int) cleanedData.getOrDefault("page", 1);
        query.setStart((currentPage - 1) * perPage);
        query.setRows(perPage);
        
        return solrClient.query(query);
    }
    
    public void close(){
        if (solrClient != null){
            try{
                solrClient.close();
            }catch(IOException e){
                //ignore this exception for closing
            }
        }
    }
}

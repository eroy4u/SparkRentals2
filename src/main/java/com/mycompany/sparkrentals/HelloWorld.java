/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import freemarker.template.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

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
        SolrClient client = new HttpSolrClient( url );       
        
        get("/hello", (req, res) -> "Hello World");
        
        get("/hello2", (request, response) -> {
           Map<String, Object> attributes = new HashMap<>();
           attributes.put("message", "Hellow World using Template");
           
           QueryResponse queryResponse = client.query(new SolrQuery("*.*"));
           
//           SolrDocumentList docs = queryResponse.getResults();
           List<Rental> rentalList = queryResponse.getBeans(Rental.class);
           
//           List resultList = new ArrayList();
//           resultList.add("haha");
           attributes.put("rentalList", rentalList);
           return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);
    }
}

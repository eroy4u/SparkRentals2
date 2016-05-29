/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import freemarker.template.Configuration;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
//import spark.

/**
 *
 * @author eroy4u
 */
public class HelloWorld {
    public static void main(String[] args) {
        Configuration viewDir = new Configuration();
        viewDir.setClassForTemplateLoading(HelloWorld.class, "/views");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(viewDir);

        get("/hello", (req, res) -> "Hello World");
        
        get("/hello2", (request, response) -> {
           Map<String, Object> attributes = new HashMap<>();
           attributes.put("message", "Hellow World using Template");
           return new ModelAndView(attributes, "hello.ftl");
        }, freeMarkerEngine);
    }
}

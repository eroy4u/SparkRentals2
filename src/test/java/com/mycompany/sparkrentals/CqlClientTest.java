/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import com.datastax.driver.core.Session;
import org.cassandraunit.DataLoader;
import org.cassandraunit.dataset.json.ClassPathJsonDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eroy4u
 */
public class CqlClientTest {
    
    public CqlClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        DataLoader dataLoader = new DataLoader("TestCluster", "localhost:9171");
        dataLoader.load(new ClassPathJsonDataSet("cql_data.json"));
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of insertOrUpdateRental method, of class CqlClient.
     */
    @org.junit.Test
    public void test_insert_ok() {
        System.out.println("insertOrUpdateRental");
        Rental rental = null;
        CqlClient instance = new CqlClient();
        instance.connect("localhost:9171", "testKeySpace");
        instance.insertOrUpdateRental(rental);
        
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import com.datastax.driver.core.ResultSet;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Rule;

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
    
    @Rule
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("sample.cql","TestKeyspace"));
        
    @Before
    public void setUp() throws Exception {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra("/cassandra.yaml");
//        DataLoader dataLoader = new DataLoader("TestCluster", "localhost:9171");
//        dataLoader.load(new ClassPathJsonDataSet("cql_data.json"));
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of insertOrUpdateRental method, of class CqlClient.
     */
    @org.junit.Test
    public void test_insert_ok() {
        ResultSet result = cassandraCQLUnit.session.execute("select * from student WHERE id='key1'");
        assertThat(result.iterator().next().getString("name"), is("Shukla"));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import java.util.Date;
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

    
    @Rule
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("sample.cql","TestKeyspace"));
        
    @Before
    public void setUp() throws Exception {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra("/cassandra.yaml");
    }
    
    @After
    public void tearDown() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }


    /**
     * Test of insertOrUpdateRental method, of class CqlClient.
     */
    @org.junit.Test
    public void test_insert_rental() {
        
        CqlClient client = new CqlClient();
        client.setSession(cassandraCQLUnit.session);
        client.setCqlKeyspace("TestKeyspace");
        
        Rental rental = new Rental();
        rental.setId("A0293");
        rental.setCity("city0");
        rental.setProvince("province1");
        rental.setCountry("country1");
        rental.setZipCode("9d8Ui");
        rental.setType("Villa");
        rental.setHasAirCondition(true);
        rental.setHasGarden(true);
        rental.setHasPool(true);
        rental.setIsCloseToBeach(true);
        rental.setDailyPrice(12.7f);
        rental.setCurrency("$");
        rental.setRoomsNumber(3);
        rental.setUpdated(new Date());
        
        client.insertOrUpdateRental(rental);
                
        ResultSet result = cassandraCQLUnit.session.execute("select * from rentals WHERE id='A0293'");
        Row row = result.iterator().next();
        assertEquals(row.getString("city"), rental.getCity());
        assertEquals(row.getString("province"), rental.getProvince());
        assertEquals(row.getString("country"), rental.getCountry());
        assertEquals(row.getString("zipCode"), rental.getZipCode());
        assertEquals(row.getString("type"), rental.getType());
        assertEquals(row.getBool("hasAirCondition"), rental.isHasAirCondition());
        assertEquals(row.getBool("hasPool"), rental.isHasPool());
        assertEquals(row.getBool("hasGarden"), rental.isHasGarden());
        assertEquals(row.getBool("isCloseToBeach"), rental.isIsCloseToBeach());
        assertEquals(row.getFloat("dailyPrice"), rental.getDailyPrice(), 0.1f);
        assertEquals(row.getString("currency"), rental.getCurrency());
        assertEquals(row.getInt("roomsNumber"), rental.getRoomsNumber());
        assertEquals(row.getDate("updated"), rental.getUpdated());
        
        
    }

}

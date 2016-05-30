/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.client;

import com.mycompany.sparkrentals.client.CqlClient;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.mycompany.sparkrentals.Rental;
import java.util.Date;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;
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
    public CassandraCQLUnit cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("sample.cql", "TestKeyspace"));

    @Before
    public void setUp() throws Exception {
        //start a embeded test db server
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
    public void testInsertRental() {

        CqlClient client = new CqlClient();
        client.setSession(cassandraCQLUnit.session);
        client.setCqlKeyspace("TestKeyspace");

        Rental rental = new Rental("A0293", "city0", "province1", "country1",
                "9d8Ui", "Villa", true, true, true, true, 12.7f, "$", 3, new Date());

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

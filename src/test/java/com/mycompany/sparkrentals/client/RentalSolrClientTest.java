/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.client;

import com.mycompany.sparkrentals.Rental;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author eroy4u
 */
public class RentalSolrClientTest {

    private SolrClient server;
    private RentalSolrClient client;

    @Rule
    public TemporaryFolder tempSolrHome= new TemporaryFolder();
    
    @Before
    public void setUp() throws IOException, SolrServerException {
        File resourceDir = new File("src/test/resources/solr");
        //use a temporary folder for solr home
        FileUtils.copyDirectory(resourceDir, tempSolrHome.getRoot());        
        CoreContainer coreContainer = new CoreContainer(tempSolrHome.getRoot().getAbsolutePath());
        coreContainer.load();
        server = new EmbeddedSolrServer(coreContainer, "new_core");
        
        //populate initial data
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental("A1234", "city1", "province2", "country2", "fjkso",
                "Villa", true, true, true, true, 10f, "$", 1, new Date()));
        rentals.add(new Rental("A2234", "city2", "province2", "country9", "dfsdd",
                "studio", true, false, true, true, 11f, "$", 2, new Date()));
        rentals.add(new Rental("A3234", "city3", "province5", "country1", "e9r3e",
                "studio", false, true, false, true, 7.2f, "$", 3, new Date()));
        Date threeDaysAgo = new Date(new Date().getTime() - 3*24*3600*1000);
        rentals.add(new Rental("A4234", "city4", "province7", "country6", "ghree",
                "Single Floor", true, false, true, true, 8f, "$", 2, threeDaysAgo));
        
        for (Rental rental: rentals){
            server.addBean(rental);
            server.commit();
        }
        
        client = new RentalSolrClient();
        client.setSolrClient(server);
    }
    /**
     * Test of searchRentals method, of class RentalSolrClient.
     */
    @Test
    public void testTimePeriod() throws IOException, SolrServerException {
        Map<String, Object> data = new HashMap<>();
        
        //created or updated in the last 2 days
        data.put("timePeriod", 2);
        List<Rental> rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        Date twoDaysAgo = new Date(new Date().getTime() - 2 * 24 * 3600 * 1000);
        for (Rental rental : rentals) {
            assertTrue(rental.getUpdated().after(twoDaysAgo));
        }
    }
    /**
     * Test of searchRentals method, of class RentalSolrClient.
     */
    @Test
    public void testSearchRentalByNumericRange() throws IOException, SolrServerException {
        Map<String, Object> data = new HashMap<>();
        
        //roomsNumber 2 or above
        data.put("roomsNumberFrom", 2);
        List<Rental> rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        boolean hasThreeRooms = false;
        for (Rental rental: rentals){
            if (rental.getRoomsNumber() == 3){
                hasThreeRooms = true;
            }
            assertTrue(rental.getRoomsNumber()>=2);
        }
        assertTrue(hasThreeRooms);
        
        //roomsNumber from 2 to 2
        data.put("roomsNumberTo", 2);
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental: rentals){
            assertTrue(rental.getRoomsNumber()==2);
        }
        
        //daillyPrice range
        data.clear();
        data.put("dailyPriceFrom", 5.1f);
        data.put("dailyPriceTo", 7.8f);
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            float dailyPrice = rental.getDailyPrice();
            assertTrue(dailyPrice <= 7.8f && dailyPrice >= 5.1f);
        }

        
    }
    /**
     * Test of searchRentals method, of class RentalSolrClient.
     */
    @Test
    public void testSearchRentalByExactFilter() throws IOException, SolrServerException {
        
        Map<String, Object> data = new HashMap<>();
        
        //test city constraint
        data.put("city", "city1");
        List<Rental> rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental: rentals){
            assertEquals(rental.getCity(), "city1");
        }
        
        //test province constraint
        data.clear();
        data.put("province", "province2");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.getProvince(), "province2");
        }
        //test country constraint
        data.clear();
        data.put("country", "country9");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.getCountry(), "country9");
        }
        
        //test country constraint
        data.clear();
        data.put("zipCode", "fjkso");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.getZipCode(), "fjkso");
        }
        
        //test type constraint
        data.clear();
        data.put("type", "Villa");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.getType(), "Villa");
        }
        
        //test has air condition constraint
        data.clear();
        data.put("hasAirCondition", "Yes");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.isHasAirCondition(), true);
        }
        
        //test has pool
        data.clear();
        data.put("hasPool", "Yes");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.isHasPool(), true);
        }
        //test has garden
        data.clear();
        data.put("hasGarden", "Yes");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.isHasGarden(), true);
        }
        //test is close to beach
        data.clear();
        data.put("isCloseToBeach", "Yes");
        rentals = client.searchRentals(data, 20).getBeans(Rental.class);
        assertTrue(rentals.size() > 0);
        for (Rental rental : rentals) {
            assertEquals(rental.isIsCloseToBeach(), true);
        }
        
    }
    /**
     * Test of addRental method, of class RentalSolrClient.
     */
    @Test
    public void testAddRental() throws IOException, SolrServerException {
        Rental rental = new Rental("A9999", "city90", "province90", "country90",
                "9d8Ui", "Villa", true, true, true, true, 120.7f, "$", 3, new Date());
        
        client.addRental(rental);
        
        SolrQuery query = new SolrQuery("id:A9999");
        QueryResponse response = server.query(query);
        List<Rental> solrResults = response.getBeans(Rental.class);
        assertEquals(solrResults.size(), 1);
        Rental solrRental = solrResults.get(0);
        assertEquals(rental.getId(), solrRental.getId());
        assertEquals(rental.getCity(), rental.getCity());
        assertEquals(rental.getProvince(), rental.getProvince());
        assertEquals(rental.getCountry(), rental.getCountry());
        assertEquals(rental.getZipCode(), rental.getZipCode());
        assertEquals(rental.getType(), rental.getType());
        assertEquals(rental.isHasAirCondition(), rental.isHasAirCondition());
        assertEquals(rental.isHasPool(), rental.isHasPool());
        assertEquals(rental.isHasGarden(), rental.isHasGarden());
        assertEquals(rental.isIsCloseToBeach(), rental.isIsCloseToBeach());
        assertEquals(rental.getDailyPrice(), rental.getDailyPrice(), 0.1f);
        assertEquals(rental.getCurrency(), rental.getCurrency());
        assertEquals(rental.getRoomsNumber(), rental.getRoomsNumber());
        assertEquals(rental.getUpdated(), rental.getUpdated());
    }

    
    
}

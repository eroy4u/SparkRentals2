/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.client;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.mycompany.sparkrentals.Rental;

/**
 *
 * @author eroy4u
 */
public class CqlClient {

    private Cluster cluster = null;
    private Session session = null;
    private String cqlKeyspace;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }

    public void connect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        session = cluster.connect();
    }

    public void setCqlKeyspace(String cqlKeyspace) {
        this.cqlKeyspace = cqlKeyspace;
    }

    public void insertOrUpdateRental(Rental rental) {

        PreparedStatement statement = session.prepare(
                "INSERT INTO " + cqlKeyspace + ".rentals "
                + "(id, city, province, country, zipCode, type, hasAirCondition, "
                + "hasGarden, hasPool, isCloseToBeach, dailyPrice, currency, "
                + "roomsNumber, updated) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

        BoundStatement boundStatement = new BoundStatement(statement);
        boundStatement.bind(rental.getId(), rental.getCity(),
                rental.getProvince(), rental.getCountry(), rental.getZipCode(),
                rental.getType(), rental.isHasAirCondition(), rental.isHasGarden(),
                rental.isHasPool(), rental.isIsCloseToBeach(), rental.getDailyPrice(),
                rental.getCurrency(), rental.getRoomsNumber(), rental.getUpdated());
        session.execute(boundStatement);

    }

    public void close() {
        if (session != null) {
            session.close();
        }
        if (cluster != null) {
            cluster.close();
        }
    }

}

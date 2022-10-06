package com.springboot.bonnwasgeht;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Events {
    final String db_url= "jdbc:mysql://localhost:3306/bonnwasgehtdb";
    final String db_username="root";
    final String password="philip_0317";

    Connection connection = null;
    Statement statement = null;

    public void addEvent(int eventid, String title, String picture, String shortdescription, String description, String startdatum, String enddatum, String opendAt, String closedAt){
        try{
            connection = DriverManager.getConnection(db_url, db_username, password);  //connection with database

            statement = connection.createStatement();

            //sql befehl
            String sql = "INSERT INTO Events(EventID, title, picture, shortdescription, description, startdatum, enddatum, opendAt, closedAt)" + "VALUES('"+ eventid + "','" + title + "','" +picture + "','" + shortdescription + "','" + description + "','" + startdatum + "','" + enddatum + "','" + opendAt + "','" + closedAt + "');";


            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

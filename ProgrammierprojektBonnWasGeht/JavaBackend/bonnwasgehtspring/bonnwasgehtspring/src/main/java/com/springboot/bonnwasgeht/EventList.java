package com.springboot.bonnwasgeht;



import org.springframework.util.MultiValueMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class EventList {
    static final String db_url= "jdbc:mysql://localhost:3306/bonnwasgehtdb";
    static final String db_username="root";
    static final String password="philip_0317";

    static Connection connection = null;
    static Statement statement = null;


    public static void addEventToDB(Event event){

        try{
            connection = DriverManager.getConnection(db_url, db_username, password);  //connection with database

            statement = connection.createStatement();

            //sql befehl
            String sql = "INSERT INTO Events(EventID, title, picture, shortdescription, description, startdatum, enddatum, opendAt, closedAt)" + "VALUES(NULL,'"+  event.getTitle() + "', NULL ,'" + event.getShortdescription() + "','" + event.getDescription() + "','" + event.getStartdatum() + "','" + event.getEnddatum() + "','" + event.getOpendAt() + "','" + event.getClosedAt() + "');";


            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

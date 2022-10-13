package com.springboot.bonnwasgeht;



import org.springframework.util.MultiValueMap;

import java.sql.*;
import java.util.ArrayList;
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

    public static ArrayList<Event> getEvents(){
     try{
        connection = DriverManager.getConnection(db_url, db_username, password);  //connection with database

        statement = connection.createStatement();

        //sql befehl
        String sql = "SELECT * FROM Events ORDER BY EventID";

        ResultSet resultset = statement.executeQuery(sql);

        ArrayList<Event> EventList = new ArrayList<>();

        //iterate throught Resultset
        while(resultset.next()){
            Event currentEvent = new Event();

            currentEvent.setTitle(resultset.getString(2));
            currentEvent.setShortdescription(resultset.getString(4));
            currentEvent.setDescription(resultset.getString(5));
            currentEvent.setStartdatum(resultset.getString(6));
            currentEvent.setEnddatum(resultset.getString(7));
            currentEvent.setOpendAt(resultset.getString(8));
            currentEvent.setClosedAt(resultset.getString(9));

            EventList.add(currentEvent);
        }
        return EventList;
    } catch (Exception e) {
        e.printStackTrace();
    }
     return null;
    }
}

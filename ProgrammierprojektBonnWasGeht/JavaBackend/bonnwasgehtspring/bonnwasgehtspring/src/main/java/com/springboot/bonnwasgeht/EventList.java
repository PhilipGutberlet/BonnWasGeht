package com.springboot.bonnwasgeht;


import java.sql.*;
import java.util.ArrayList;

public class EventList {
    static final String db_url= "jdbc:mysql://localhost:3306/bonnwasgehtdb";
    static final String db_username="root";
    static final String password="philip_0317";

    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement= null;

    public static void addEventToDB(Event event){

        try{
            connection = DriverManager.getConnection(db_url, db_username, password);  //connection with database


            //sql befehl
            String sql = "INSERT INTO Events(EventID, title, shortdescription, description, startdatum, enddatum, opendAt, closedAt,picture) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";  //+ "VALUES(NULL,'"+  event.getTitle() +"','"+ picture +"','"+event.getShortdescription()+"','" + event.getDescription() + "','" + event.getStartdatum() + "','" + event.getEnddatum() + "','" + event.getOpendAt() + "','" + event.getClosedAt() + "');";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, null);
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setString(3, event.getShortdescription());
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setString(5, event.getStartdatum());
            preparedStatement.setString(6, event.getEnddatum());
            preparedStatement.setString(7, event.getOpendAt());
            preparedStatement.setString(8, event.getClosedAt());
            preparedStatement.setString(9, event.getPicturename());

            preparedStatement.executeUpdate();

            //statement.executeUpdate(sql);

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

            currentEvent.setShortdescription(resultset.getString(3));

            currentEvent.setDescription(resultset.getString(4));

            currentEvent.setStartdatum(resultset.getString(5));

            currentEvent.setEnddatum(resultset.getString(6));

            currentEvent.setOpendAt(resultset.getString(7));

            currentEvent.setClosedAt(resultset.getString(8));

            currentEvent.setPicturename(resultset.getString(9));

            currentEvent.setBytearray(FileStorageSystem.getImageAsByteArray(resultset.getString(9)));

            EventList.add(currentEvent);
        }
        return EventList;
    } catch (Exception e) {
        e.printStackTrace();
    }
     return null;
    }

    public static String convertDate(String Date){
        int month = Integer.parseInt(Date.substring(5, 7));
        System.out.println(month);
        String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        return "bis "+ Date.substring(Date.length()-2)+". "+months[month-1];
    }

    public static String convertTime(String Time){
        return Time.substring(0, Time.length()-3);
    }
}

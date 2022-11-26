package com.springboot.bonnwasgeht;


import java.io.IOException;
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
            String sql = "INSERT INTO Events(title, shortdescription, description, startdatum, enddatum, opendAt, closedAt, picture, section) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getShortdescription());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setString(4, event.getStartdatum());
            preparedStatement.setString(5, event.getEnddatum());
            preparedStatement.setString(6, event.getOpendAt());
            preparedStatement.setString(7, event.getClosedAt());
            preparedStatement.setString(8, event.getPicturename());
            preparedStatement.setString(9, event.getSection());

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

        ArrayList<Event> eventList = new ArrayList<>();

        //iterate throught Resultset
        while(resultset.next()){
            Event currentEvent = new Event();

            currentEvent.setEventID(resultset.getInt(1));


            currentEvent.setTitle(resultset.getString(2));

            currentEvent.setShortdescription(resultset.getString(3));

            currentEvent.setDescription(resultset.getString(4));

            currentEvent.setStartdatum(EventList.convertDate(resultset.getString(5)));

            currentEvent.setEnddatum(EventList.convertDate(resultset.getString(6)));

            currentEvent.setOpendAt(EventList.convertTime(resultset.getString(7)));

            currentEvent.setClosedAt(EventList.convertTime(resultset.getString(8)));

            currentEvent.setPicturename(resultset.getString(9));

            currentEvent.setBytearray(FileStorageSystem.getImageAsByteArray(resultset.getString(9)));

            currentEvent.setSection(resultset.getString(10));

            eventList.add(currentEvent);
        }
        return eventList;
    } catch (Exception e) {
        e.printStackTrace();
    }
     return null;
    }

    public static Event getEvent(int EventID){

        try {
            statement = connection.createStatement();
            connection = DriverManager.getConnection(db_url, db_username, password);  //connection with database

            //sql befehl
            String sql = "SELECT * FROM Events WHERE EventID="+"'"+ EventID +"'";

            ResultSet resultset = statement.executeQuery(sql);
            while(resultset.next()) {
                Event event = new Event();

                event.setTitle(resultset.getString(2));
                event.setDescription(resultset.getString(4));
                event.setPicturename(resultset.getString(9));
                event.setBytearray(FileStorageSystem.getImageAsByteArray(resultset.getString(9)));


                return event;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDate(String Date){
        int month = Integer.parseInt(Date.substring(5, 7));
        String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        return Date.substring(Date.length()-2)+". "+months[month-1];
    }

    public static String convertTime(String Time){
        return Time.substring(0, Time.length()-3);
    }
}

package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventManager.EventInfo;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBEventInfo {

public static ArrayList<EventInfo> getAllEvents() throws SQLException, ClassNotFoundException, ParseException {

    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();


    ret = DatabaseHandler.connectToDatabase();



    String request = "select * from EventInfo";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);




        while(rs.next()) {

            EventInfo eventInfo = generateEventInfoFromResultSet(rs);
            eList.add(eventInfo);


        }

    return eList;
}
public static ArrayList<EventInfo> getEventsInfoByPriceRange(double min, double max) throws SQLException, ClassNotFoundException, ParseException {
    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();


    ret = DatabaseHandler.connectToDatabase();



    String request = "select * from EventInfo where ePrice <= "+max+" and ePrice >= "+min;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);



        while(rs.next()) {

            EventInfo eventInfo = generateEventInfoFromResultSet(rs);

            eList.add(eventInfo);


        }

    return eList;
}

public static ArrayList<EventInfo> getEventsInfoByEventLocation(String location) throws SQLException, ClassNotFoundException, ParseException {


    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();


    ret = DatabaseHandler.connectToDatabase();


    String request = "select * from EventInfo where eLocation like '%"+location+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);




        while(rs.next()) {

            EventInfo eventInfo = generateEventInfoFromResultSet(rs);
            eList.add(eventInfo);


        }


    return eList;
}

public static ArrayList<EventInfo> getEventsInfoByEventTitle(String title) throws SQLException, ClassNotFoundException, ParseException {


    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();


    ret = DatabaseHandler.connectToDatabase();



    String request = "select * from EventInfo where eTitle like '%"+title+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);




        while(rs.next()) {

            EventInfo eventInfo = generateEventInfoFromResultSet(rs);
            eList.add(eventInfo);


        }


    return eList;
}

public static ArrayList<EventInfo> getEventsInfoByEventOrganizer(int userId) throws SQLException, ClassNotFoundException, ParseException {


    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();


    ret = DatabaseHandler.connectToDatabase();


    String request = "select * from EventInfo where eOrgId = "+userId;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);




    while(rs.next()) {

        EventInfo eventInfo = generateEventInfoFromResultSet(rs);
        eList.add(eventInfo);


    }


    return eList;
}

//public static EventInfo getEventInfoByEventId(int id) throws SQLException, ClassNotFoundException, ParseException {
//
//    EventTypes.Return ret = EventTypes.Return.SUCCESS;
//
//
//
//    ret = DatabaseHandler.connectToDatabase();
//
//
//    String request = "select * from EventInfo where id="+id;
//
//    ResultSet rs = DatabaseHandler.selectFromDatabase(request);
//
//
//
//    rs.next();
//
//    EventInfo eventInfo = generateEventInfoFromResultSet(rs);
//
//    return eventInfo;
//}

    public static EventTypes.Return insertEventIntoDB(EventInfo ei)
    {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        try {
            ret = DatabaseHandler.connectToDatabase();


            ret = DatabaseHandler.insertIntoTable(ei);


            DatabaseHandler.closeConnection();
        }
        catch (Exception e)
        {
            EventTypes.Return.printError(EventTypes.Return.INSERTFAILED);
            return EventTypes.Return.INSERTFAILED;
        }


        return ret;

    }

    public static EventTypes.Return updateEvent(EventInfo ei){

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        try {

            ret = DatabaseHandler.connectToDatabase();


            ret = DatabaseHandler.updateTable(ei);


            ret = DatabaseHandler.closeConnection();
        }catch (Exception e)
        {
            EventTypes.Return.printError(EventTypes.Return.UPDATEFAILED);
            return EventTypes.Return.UPDATEFAILED;
        }

        return ret;

    }

    public static EventTypes.Return deleteEvent(EventInfo ei) {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        try {
            ret = DatabaseHandler.connectToDatabase();


            ret = DatabaseHandler.deleteFromTable(ei);


            DatabaseHandler.closeConnection();
        }
        catch (Exception e)
        {
            EventTypes.Return.printError(EventTypes.Return.DELETEFAILED);
            return EventTypes.Return.DELETEFAILED;
        }


        return ret;


    }

    private static EventInfo generateEventInfoFromResultSet(ResultSet rs) throws SQLException, ParseException {

        int eId = -1;
        String eTitle = null;
        String eLocation= null;
        String eStart= null;
        String eEnd= null;
        String eDescription= null;
        int eOrgId= -1;
        float ePrice= 0;
        int eAvailable= 0;
        int eReserved= 0;

        eId = rs.getInt(1);
        eTitle = rs.getString(2);
        eLocation = rs.getString(3);
        eStart = new SimpleDateFormat("MM/dd/yy - HH:mm").format(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(4).split("\\.")[0]));
        eEnd = new SimpleDateFormat("MM/dd/yy - HH:mm").format(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5).split("\\.")[0]));
        eDescription = rs.getString(6);
        eOrgId = rs.getInt(7);
        ePrice = rs.getFloat(8);
        eAvailable = rs.getInt(9);
        eReserved = rs.getInt(10);

        EventInfo eventInfo = new EventInfo();

        eventInfo.SetEventInfos(eId, eTitle, eLocation, eStart, eEnd, eDescription, eOrgId, ePrice, eAvailable, eReserved);

        return eventInfo;
    }


}

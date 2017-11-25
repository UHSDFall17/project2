package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventManager.EventInfo;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.*;
import java.util.ArrayList;

public class DBEventInfo {


public static ArrayList<EventInfo> getEventsInfoByPriceRange(double min, double max) {
    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();

    int eId =-1;
    String eTitle = null;
    String eLocation= null;
    String eStart= null;
    String eEnd= null;
    String eDescription= null;
    int eOrgId= -1;
    float ePrice= 0;
    int eAvailable= 0;
    int eReserved= 0;

    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where ePrice <= "+max+" and ePrice >= "+min;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;

    try {

        while(rs.next()) {

            eId = rs.getInt(1);
            eTitle = rs.getString(2);
            eLocation = rs.getString(3);
            eStart = rs.getString(4);
            eEnd = rs.getString(5);
            eDescription = rs.getString(6);
            eOrgId = rs.getInt(7);
            ePrice = rs.getFloat(8);
            eAvailable = rs.getInt(9);
            eReserved = rs.getInt(10);

            EventInfo eventInfo = new EventInfo();

            eventInfo.SetEventInfos(eId, eTitle, eLocation, eStart, eEnd, eDescription, eOrgId, ePrice, eAvailable, eReserved);

            eList.add(eventInfo);


        }

    } catch (SQLException e) {
        return null;
    }

    return eList;
}
public static ArrayList<EventInfo> getEventsInfoByEventLocation(String location) {


    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();

    int eId =-1;
    String eTitle = null;
    String eLocation= null;
    String eStart= null;
    String eEnd= null;
    String eDescription= null;
    int eOrgId= -1;
    float ePrice= 0;
    int eAvailable= 0;
    int eReserved= 0;

    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where eLocation like '%"+location+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;

    try {

        while(rs.next()) {

            eId = rs.getInt(1);
            eTitle = rs.getString(2);
            eLocation = rs.getString(3);
            eStart = rs.getString(4);
            eEnd = rs.getString(5);
            eDescription = rs.getString(6);
            eOrgId = rs.getInt(7);
            ePrice = rs.getFloat(8);
            eAvailable = rs.getInt(9);
            eReserved = rs.getInt(10);

            EventInfo eventInfo = new EventInfo();

            eventInfo.SetEventInfos(eId, eTitle, eLocation, eStart, eEnd, eDescription, eOrgId, ePrice, eAvailable, eReserved);

            eList.add(eventInfo);


        }

    } catch (SQLException e) {
        return null;
    }

    return eList;
}

public static ArrayList<EventInfo> getEventsInfoByEventTitle(String title) {


    EventTypes.Return ret = EventTypes.Return.SUCCESS;
    ArrayList<EventInfo> eList = new ArrayList<>();

    int eId =-1;
    String eTitle = null;
    String eLocation= null;
    String eStart= null;
    String eEnd= null;
    String eDescription= null;
    int eOrgId= -1;
    float ePrice= 0;
    int eAvailable= 0;
    int eReserved= 0;

    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where eTitle like '%"+title+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;

    try {

        while(rs.next()) {

            eId = rs.getInt(1);
            eTitle = rs.getString(2);
            eLocation = rs.getString(3);
            eStart = rs.getString(4);
            eEnd = rs.getString(5);
            eDescription = rs.getString(6);
            eOrgId = rs.getInt(7);
            ePrice = rs.getFloat(8);
            eAvailable = rs.getInt(9);
            eReserved = rs.getInt(10);

            EventInfo eventInfo = new EventInfo();

            eventInfo.SetEventInfos(eId, eTitle, eLocation, eStart, eEnd, eDescription, eOrgId, ePrice, eAvailable, eReserved);

            eList.add(eventInfo);


        }

    } catch (SQLException e) {
        return null;
    }

    return eList;
}

public static EventInfo getEventInfoByEventId(int id) {

    EventInfo eventInfo = new EventInfo();
    EventTypes.Return ret = EventTypes.Return.SUCCESS;

    int eId = id;
    String eTitle = null;
    String eLocation= null;
    String eStart= null;
    String eEnd= null;
    String eDescription= null;
    int eOrgId= -1;
    float ePrice= 0;
    int eAvailable= 0;
    int eReserved= 0;


    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where id="+id;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


    try {

        rs.next();

        eId = rs.getInt(1);
        eTitle = rs.getString(2);
        eLocation = rs.getString(3);
        eStart = rs.getString(4);
        eEnd = rs.getString(5);
        eDescription = rs.getString(6);
        eOrgId = rs.getInt(7);
        ePrice  = rs.getFloat(8);
        eAvailable = rs.getInt(9);
        eReserved = rs.getInt(10);

    } catch (SQLException e) {
        return null;
    }

    eventInfo.SetEventInfos(eId, eTitle, eLocation, eStart, eEnd, eDescription, eOrgId, ePrice, eAvailable, eReserved);


    return eventInfo;
}

    public static EventTypes.Return insertEventIntoDB(EventInfo ei) {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = DatabaseHandler.connectToDatabase();

        if(!ret.equals(EventTypes.Return.SUCCESS))
            return ret;

        ret = DatabaseHandler.insertIntoTable(ei);

        if(!ret.equals(EventTypes.Return.SUCCESS)) {

            DatabaseHandler.closeConnection();
            return ret;
        }

        ret = DatabaseHandler.closeConnection();

        return ret;

    }


}

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

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


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

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where ePrice <= "+max+" and ePrice >= "+min;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


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

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where eLocation like '%"+location+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


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

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where eTitle like '%"+title+"%'";

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


        while(rs.next()) {

            EventInfo eventInfo = generateEventInfoFromResultSet(rs);
            eList.add(eventInfo);


        }


    return eList;
}

public static EventInfo getEventInfoByEventId(int id) throws SQLException, ClassNotFoundException, ParseException {

    EventTypes.Return ret = EventTypes.Return.SUCCESS;



    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from EventInfo where id="+id;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


    rs.next();

    EventInfo eventInfo = generateEventInfoFromResultSet(rs);

    return eventInfo;
}

    public static EventTypes.Return insertEventIntoDB(EventInfo ei) throws SQLException, ClassNotFoundException {

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

    public static EventTypes.Return updateEvent(EventInfo ei) throws SQLException, ClassNotFoundException {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = DatabaseHandler.connectToDatabase();

        if(!ret.equals(EventTypes.Return.SUCCESS))
            return ret;

        try {
            ret = DatabaseHandler.updateTable(ei);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!ret.equals(EventTypes.Return.SUCCESS)) {

            DatabaseHandler.closeConnection();
            return ret;
        }

        ret = DatabaseHandler.closeConnection();

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

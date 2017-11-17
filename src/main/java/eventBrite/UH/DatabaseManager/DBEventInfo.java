package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventCreate.EventInfo;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.*;

public class DBEventInfo {



    public static EventInfo getEventInfoFromEventId(int id) {

        EventInfo eventInfo = new EventInfo();
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        String eTitle = null;
        String eLocation= null;
        String eStart= null;
        String eEnd= null;
        String eDescription= null;
        String eOrgName= null;
        float ePrice= 0;
        int eAvailable= 0;
        int eReserved= 0;


        ret = DatabaseHandler.connectToDatabase();

        if(!ret.equals(EventTypes.Return.SUCCESS))
            return null;

        String request = "select * from eventinfo where id="+id;

        ResultSet rs = DatabaseHandler.selectFromDatabase(request);

        if(rs == null)
            return null;


        try {

            rs.next();


            eTitle = rs.getString(2);
            eLocation = rs.getString(3);
            eStart = rs.getString(4);
            eEnd = rs.getString(5);
            eDescription = rs.getString(6);
            eOrgName = rs.getString(7);
            ePrice  = rs.getFloat(8);
            eAvailable = rs.getInt(9);
            eReserved = rs.getInt(10);

        } catch (SQLException e) {
            return null;
        }

        eventInfo.SetEventInfos(eTitle, eLocation, eStart, eEnd, eDescription, eOrgName, ePrice, eAvailable, eReserved);


        return eventInfo;
    }
}

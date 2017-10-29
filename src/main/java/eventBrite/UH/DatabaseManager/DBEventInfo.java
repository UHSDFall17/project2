package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventCreate.EventInfo;
import eventBrite.UH.EventTools.EventTypes;
import javafx.event.EventType;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
        String eOrgDesc= null;
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
            eOrgDesc = rs.getString(8);
            ePrice  = rs.getFloat(9);
            eAvailable = rs.getInt(10);
            eReserved = rs.getInt(11);

        } catch (SQLException e) {
            return null;
        }

        eventInfo.SetEventInfos(eTitle, eLocation, eStart, eEnd, eDescription, eOrgName, eOrgDesc, ePrice, eAvailable, eReserved);


        return eventInfo;
    }
}

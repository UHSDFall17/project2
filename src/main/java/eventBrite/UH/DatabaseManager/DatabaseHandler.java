package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventTools.AttributesGetter;
import eventBrite.UH.EventTools.EventTypes;

import java.lang.reflect.Field;
import java.sql.*;

public class DatabaseHandler {

    private static Connection con;

    public static EventTypes.Return connectToDatabase()
    {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2?autoReconnect=true&useSSL=false","root","Project2!" );
        }
        catch (Exception e) {
            return EventTypes.Return.CONNECTIONFAILED;
        }

    return EventTypes.Return.SUCCESS;

    }

    public static EventTypes.Return closeConnection() {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;
        try {
            con.close();
        }
        catch (Exception e)
        {
            return EventTypes.Return.CLOSEFAILED;
        }

        return ret;
    }


    public static ResultSet  selectFromDatabase(String request)
    {
        ResultSet rs;
        try {
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(request);
        }
        catch(Exception e)
        {
            return null;
        }

        return rs;
    }

    public static <T extends AttributesGetter> EventTypes.Return insertIntoTable(T obj)
    {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;
        String request = "insert into ";
        String tableName = obj.getClass().getSimpleName();

        request = request + tableName + "(";

        Field[] fd = obj.getClass().getDeclaredFields();

        String values = " values (";
        String fdType;
        for (int i=0; i<fd.length; i++)
        {
            if(fd[i].getName().equals("id"))
                continue;
            request = request + fd[i].getName();
            fdType = fd[i].getType().getSimpleName();
            if (fdType.equals("String") || fdType.equals("Date"))
                values = values +"'"+ obj.getByName(fd[i].getName()) + "'";
            else
                values = values + obj.getByName(fd[i].getName());


            if(i!=fd.length-1) {
                request = request + ",";
                values = values + ",";
            }
        }

        request = request + ")" + values +")";


        //String sql = "insert into EventInfo(eTitle,eLocation,eStart,eEnd,eDescription,eOrgName,ePrice,eAvailable,eReserved) values ('event1','UH','2017-02-17 - 10:00:00','2017-02-17 - 10:00:00','this is event1','org1',7.0,20,0)";
//        System.out.println(request);
//        return EventTypes.Return.SUCCESS;
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(request);
            System.out.println(rs);
            return ret;
        }catch (Exception e)
        {
            return EventTypes.Return.INSERTFAILED;

        }

    }

//    public static <T extends AttributesGetter> void updateTable(T obj) throws SQLException
//    {
//        String request = "insert into ";
//        String tableName = obj.getClass().getSimpleName();
//
//        request = request + tableName + "(";
//
//        Field[] fd = obj.getClass().getDeclaredFields();
//
//        String values = " values (";
//        String fdType;
//        for (int i=0; i<fd.length; i++)
//        {
//            request = request + fd[i].getName();
//            fdType = fd[i].getType().getSimpleName();
//            values = values + obj.getByName(fd[i].getName());
//
//
//            if(i!=fd.length-1) {
//                request = request + ",";
//                values = values + ",";
//            }
//        }
//
//        request = request + ")" + values +")";
//
//
//
////        System.out.println(request);
//
//        Statement stmt=con.createStatement();
//        ResultSet rs=stmt.executeQuery(request);
//
//    }
//    public void createTable(String tableName, Map<String, Class<?>> map)
//    {
//        if(map.get("hello") == String.class)
//        {
//
//        }
//
//    }
}

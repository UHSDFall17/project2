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

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2","root","Project2!" );

        }
        catch (Exception e) {
            return EventTypes.Return.CONNECTIONFAILED;
        }

    return EventTypes.Return.SUCCESS;

    }

    public static void closeConnection() throws SQLException {
        con.close();
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

    public static <T extends AttributesGetter> void insertIntoTable(T obj) throws SQLException
    {
        String request = "insert into ";
        String tableName = obj.getClass().getSimpleName();

        request = request + tableName + "(";

        Field[] fd = obj.getClass().getDeclaredFields();

        String values = " values (";
        String fdType;
        for (int i=0; i<fd.length; i++)
        {
            request = request + fd[i].getName();
            fdType = fd[i].getType().getSimpleName();
            values = values + obj.getByName(fd[i].getName());


            if(i!=fd.length-1) {
                request = request + ",";
                values = values + ",";
            }
        }

        request = request + ")" + values +")";



//        System.out.println(request);

        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(request);

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

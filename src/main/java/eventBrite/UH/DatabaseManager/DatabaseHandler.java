package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventTools.AttributesGetter;
import eventBrite.UH.EventTools.EventTypes;

import java.lang.reflect.Field;
import java.sql.*;

public class DatabaseHandler {

    private static Connection con;
    private static String db = "project2";
    private static String user = "root";

    public static void setDB(String database)
    {
        db = database;
    }

    public static void setUser(String User)
    {
        user = User;
    }

    public static EventTypes.Return connectToDatabase() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db+"?autoReconnect=true&useSSL=false",user,"Project2!" );


    return EventTypes.Return.SUCCESS;

    }

    public static EventTypes.Return closeConnection() throws SQLException {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        con.close();


        return ret;
    }


    public static ResultSet  selectFromDatabase(String request) throws SQLException {
        ResultSet rs = null;

        Statement stmt = con.createStatement();

        rs = stmt.executeQuery(request);


        return rs;
    }

    public static <T extends AttributesGetter> EventTypes.Return insertIntoTable(T obj) throws SQLException {
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



        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(request);
        System.out.println(rs);
        return ret;


    }

    public static <T extends AttributesGetter> EventTypes.Return updateTable(T obj) throws SQLException {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        String request = "update ";
        String tableName = obj.getClass().getSimpleName();

        request = request + tableName + " set ";

        Field[] fd = obj.getClass().getDeclaredFields();

        String fdType;
        for (int i=0; i<fd.length; i++)
        {
            if(fd[i].getName().equals("id"))
                continue;

            request = request + fd[i].getName() + " = ";
            fdType = fd[i].getType().getSimpleName();
            if (fdType.equals("String") || fdType.equals("Date"))
                request = request +"'"+ obj.getByName(fd[i].getName()) + "'";
            else
                request = request + obj.getByName(fd[i].getName());




            if(i!=fd.length-1) {
                request = request + ",";

            }
        }

        request = request + " where id =" + obj.getByName("id");




        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(request);
        return ret;

    }
    public static <T extends AttributesGetter> EventTypes.Return deleteFromTable(T obj) throws SQLException
    {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        String request = "delete from ";
        String tableName = obj.getClass().getSimpleName();

        request = request + tableName + " where id =" + obj.getByName("id");



        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(request);
        return ret;


    }
}

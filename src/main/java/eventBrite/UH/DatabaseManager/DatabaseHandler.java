package eventBrite.UH.DatabaseManager;

import eventBrite.UH.EventTools.EventTypes;

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

    public  ResultSet insertIntoOrUpdateDatabase(String request) throws SQLException
    {
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(request);

        return rs;
    }

    public void createTable()
    {}
}

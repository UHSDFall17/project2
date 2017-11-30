package eventBrite.UH.DatabaseManager;

import eventBrite.UH.AccountManager.UserInfo;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserInfo {

    public static UserInfo getUserInfoByUserEmail(String email) throws SQLException, ClassNotFoundException {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;




        ret = DatabaseHandler.connectToDatabase();

        if (!ret.equals(EventTypes.Return.SUCCESS))
            return null;

        String request = "select * from UserInfo where email='" + email+"'";

        ResultSet rs = DatabaseHandler.selectFromDatabase(request);

        rs.next();

        UserInfo userInfo = generateUserInfoFromResultSet(rs);



        return userInfo;
}


public static UserInfo getUserInfoByUserId(int id) throws SQLException, ClassNotFoundException {

    EventTypes.Return ret = EventTypes.Return.SUCCESS;



    ret = DatabaseHandler.connectToDatabase();


    String request = "select * from UserInfo where id="+id;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);





    rs.next();

    UserInfo userInfo = generateUserInfoFromResultSet(rs);


    return userInfo;
}

public static EventTypes.Return insertUserIntoDB(UserInfo ui){

    EventTypes.Return ret = EventTypes.Return.SUCCESS;

    try {

        ret = DatabaseHandler.connectToDatabase();


        ret = DatabaseHandler.insertIntoTable(ui); //to be added after implementation of attribute getter

        ret = DatabaseHandler.closeConnection();
    }
    catch (Exception e)
    {
        EventTypes.Return.printError(EventTypes.Return.INSERTFAILED);
        return EventTypes.Return.INSERTFAILED;
    }

    return ret;

}

    public static EventTypes.Return updateUser(UserInfo ui){

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        try {

            ret = DatabaseHandler.connectToDatabase();


            ret = DatabaseHandler.updateTable(ui);

            ret = DatabaseHandler.closeConnection();
        }
        catch (Exception e)
        {
            EventTypes.Return.printError(EventTypes.Return.UPDATEFAILED);
            return EventTypes.Return.UPDATEFAILED;
        }

        return ret;

    }

    private static UserInfo generateUserInfoFromResultSet(ResultSet rs) throws SQLException {
        int id;
        String firstName;
        String lastName;
        String email;
        String password;

        id = rs.getInt(1);
        firstName = rs.getString(3);
        lastName = rs.getString(4);
        email = rs.getString(5);
        password = rs.getString(6);

        UserInfo userInfo = new UserInfo(firstName, lastName, email, password);
        userInfo.setId(id);

        return userInfo;
    }



}

package eventBrite.UH.DatabaseManager;

import eventBrite.UH.AccountManager.UserInfo;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserInfo {

    public static UserInfo getUserInfoByUserEmail(String email) {

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        int id;
        String firstName;
        String lastName;
        String uEmail;
        String password;


        ret = DatabaseHandler.connectToDatabase();

        if (!ret.equals(EventTypes.Return.SUCCESS))
            return null;

        String request = "select * from UserInfo where email=" + email;

        ResultSet rs = DatabaseHandler.selectFromDatabase(request);

        if (rs == null)
            return null;


        try {

            rs.next();

            id = rs.getInt(1);
            firstName = rs.getString(3);
            lastName = rs.getString(4);
            email = rs.getString(5);
            password = rs.getString(6);

        } catch (SQLException e) {
            return null;
        }

        UserInfo userInfo = new UserInfo(firstName, lastName, email, password);
        userInfo.setId(id);


        return userInfo;
}


public static UserInfo getUserInfoByUserId(int id) {

    EventTypes.Return ret = EventTypes.Return.SUCCESS;

    int uId;
    String 	firstName;
    String 	lastName;
    String 	email;
    String 	password;



    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return null;

    String request = "select * from UserInfo where id="+id;

    ResultSet rs = DatabaseHandler.selectFromDatabase(request);

    if(rs == null)
        return null;


    try {

        rs.next();

        uId = rs.getInt(1);
        firstName = rs.getString(3);
        lastName = rs.getString(4);
        email = rs.getString(5);
        password = rs.getString(6);

    } catch (SQLException e) {
        return null;
    }

    UserInfo userInfo = new UserInfo(firstName,lastName,email,password);
    userInfo.setId(uId);



    return userInfo;
}

public static EventTypes.Return insertEventIntoDB(UserInfo ui) {

    EventTypes.Return ret = EventTypes.Return.SUCCESS;

    ret = DatabaseHandler.connectToDatabase();

    if(!ret.equals(EventTypes.Return.SUCCESS))
        return ret;

    //ret = DatabaseHandler.insertIntoTable(ui); //to be added after implementation of attribute getter

    if(!ret.equals(EventTypes.Return.SUCCESS)) {

        DatabaseHandler.closeConnection();
        return ret;
    }

    ret = DatabaseHandler.closeConnection();

    return ret;

}




}

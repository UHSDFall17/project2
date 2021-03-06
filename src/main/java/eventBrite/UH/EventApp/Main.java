package eventBrite.UH.EventApp;

import eventBrite.UH.DatabaseManager.DBEventInfo;
import eventBrite.UH.EventManager.EventHandler;
import eventBrite.UH.AccountManager.AccountHandler;
import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventTypes.AccountType;
import eventBrite.UH.AccountManager.UserAccount;
import eventBrite.UH.EventTools.EventInputScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static       UserAccount         userAccount    = null;
    private static final AccountHandler      accountHandler = AccountHandler.getInstance();
    private static final EventHandler        eventHandler   = EventHandler.getInstance();
    private static final ArrayList<String>   featureList    = new ArrayList<String>(Arrays.asList(
            "Login/Create Account", "create an event","update my events","delete an event",
            "search event", "Update Profile", "Exit"));

    public static void main(String[] args) {
        Return ret = Return.SUCCESS;
        System.out.println("\tWelcome to EventBrite-UH Version\n");
        ret = loadWelcomePage();

        while(ret == Return.SUCCESS)
        {
            ret = loadMainPage();
        }
        System.out.println(ret);
    }

    public static UserAccount getUserAccount() {return userAccount;}
    private static Return loadWelcomePage()
    {
        int resp;
        Scanner sc = EventInputScanner.getScanner();

        System.out.println("Please select an option [1,2,3]:");
        System.out.println("\t1- Continue as Guest");
        System.out.println("\t2- Login");
        System.out.println("\t3- Create an account");

        try
        {
            resp = Integer.parseInt(sc.nextLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
            return loadWelcomePage();
        }

        switch(resp)
        {
            case 1:
                userAccount = null;
                break;
            case 2:
                userAccount = accountHandler.login();
                break;
            case 3:
                userAccount = accountHandler.create(AccountType.MEMBER);
                break;
            default:
                return loadWelcomePage();
        }

        if(userAccount == null)
            userAccount = accountHandler.create(AccountType.GUEST);

        if(userAccount.isMember())
            System.out.println("Welcome " + userAccount.getUserInfo().getFirstname());
        else
            System.out.println("You will continue as a Guest");

        return Return.SUCCESS;
    }

    private static Return loadMainPage()
    {
        int select;
        Scanner sc = EventInputScanner.getScanner();

        for(int ftcpt = 0; ftcpt < featureList.size(); ftcpt ++)
        {
            System.out.println("input "+ ftcpt +" to access feature "+ featureList.get(ftcpt));
        }

        System.out.println();
        System.out.println("please input the feature you want to perform");

        try
        {
            select = Integer.parseInt(sc.nextLine());
        }
        catch(Exception e)
        {
            System.out.println(e);
            return loadMainPage();
        }

        switch (featureList.get(select))
        {
            case "Login/Create Account":
                System.out.println("you started "+featureList.get(select));
                loadWelcomePage();
                break;
            case "create an event":
                System.out.println("you started "+featureList.get(select));
                userAccount.createEvent();
                break;
            case "search event":
                System.out.println("you started "+featureList.get(select));
                userAccount.searchEvent();
                break;
            case "update my events" :
                System.out.println("you started "+featureList.get(select));
                userAccount.updateEvent();
                break;
            case "delete an event":
                System.out.println("you started "+featureList.get(select));
                userAccount.deleteEvent();
                break;
            case "Update Profile":
                System.out.println("you started "+featureList.get(select));
                userAccount.updateProfile();
                break;            
            case "exit":
            default:
                return Return.EXIT;
        }
        return Return.SUCCESS;
    }
}

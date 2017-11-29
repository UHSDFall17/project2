package eventBrite.UH.EventManager;

import eventBrite.UH.DatabaseManager.DBEventInfo;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventSearch {

    private ArrayList<EventInfo> eList = null;
    private static EventSearch es = new EventSearch();

    public static EventSearch getInstance( ) {
        return es;
    }


    public EventTypes.Return searchEvent()
    {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        Scanner sc = EventInputScanner.getScanner();

        System.out.println("1- Search all events");
        System.out.println("2- Search by name");
        System.out.println("3- Search by location");
        System.out.println("4- Search by price");
        System.out.println("5- return");
        try {
            int index = Integer.parseInt(sc.nextLine());

            switch (index) {
                case 1:
                    ret = SearchAll();
                    break;
                case 2:
                    System.out.println("Input an event name");
                    ret = SearchbyTitle(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Input an event location");
                    ret = SearchByLocation(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Input a minimum price");
                    double min = Double.parseDouble(sc.nextLine());
                    System.out.println("Input a maximum price");
                    double max = Double.parseDouble(sc.nextLine());
                    ret = SearchByPriceRange(min, max);
                    break;
                case 5:
                    ret = EventInputScanner.continueOrReset("Return to previous menu");
                    if (ret == EventTypes.Return.CONTINUE) {
                        return EventTypes.Return.SUCCESS;
                    }
                default:
                    throw new Exception();

            }

            if (ret == EventTypes.Return.SUCCESS) {
                ret = EventInputScanner.continueOrReset("Show an event");

                if (ret == EventTypes.Return.CONTINUE) {
                    System.out.println("Which event do you want to display ?");
                    int eindex = Integer.parseInt(sc.nextLine());
                    System.out.println(eList.get(eindex).toStringForJoiner());

                    ret = EventInputScanner.continueOrReset("Join This event");
                    if (ret == EventTypes.Return.CONTINUE) {
                        EventHandler.getInstance().join(eList.get(eindex));
                    }

                }





            } else if (ret == EventTypes.Return.EMPTYLIST)
            {
                    EventTypes.Return.printError(ret);
            }

            ret = EventInputScanner.continueOrReset("New search");

            if (ret == EventTypes.Return.CONTINUE) {
                return searchEvent();

            }
        }
        catch(Exception e)
        {
            return searchEvent();
        }

        return EventTypes.Return.SUCCESS;
    }

    private EventTypes.Return SearchAll() throws ParseException, SQLException, ClassNotFoundException {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;


        eList = DBEventInfo.getAllEvents();


        if(eList.size() == 0)
            return EventTypes.Return.EMPTYLIST;

        for(int i=0; i<eList.size();i++)
        {
            EventInfo ei = eList.get(i);
            System.out.println(i + " - " + ei.geteTitle());
        }



        return ret;
    }

    private EventTypes.Return SearchbyTitle(String title) throws ParseException, SQLException, ClassNotFoundException {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;


        eList = DBEventInfo.getEventsInfoByEventTitle(title);


        if(eList.size() == 0)
            return EventTypes.Return.EMPTYLIST;

        for(int i=0; i<eList.size();i++)
        {
            EventInfo ei = eList.get(i);
            System.out.println(i + " - " + ei.geteTitle());
        }



        return ret;
    }

    private EventTypes.Return SearchByLocation(String location) throws ParseException, SQLException, ClassNotFoundException {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;


        eList = DBEventInfo.getEventsInfoByEventLocation(location);


        if(eList.size() == 0)
            return EventTypes.Return.EMPTYLIST;

        for(int i=0; i<eList.size();i++)
        {
            EventInfo ei = eList.get(i);
            System.out.println(i + " - " + ei.geteTitle());
        }



        return ret;
    }

    private EventTypes.Return SearchByPriceRange(double min, double max) throws ParseException, SQLException, ClassNotFoundException {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;


        eList = DBEventInfo.getEventsInfoByPriceRange(min,max);


        if(eList.size() == 0)
            return EventTypes.Return.EMPTYLIST;

        for(int i=0; i<eList.size();i++)
        {
            EventInfo ei = eList.get(i);
            System.out.println(i + " - " + ei.geteTitle());
        }



        return ret;
    }
}

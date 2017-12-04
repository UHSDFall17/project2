package eventBrite.UH.EventManager;

import eventBrite.UH.DatabaseManager.DBEventInfo;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class EventController {



    private static EventController ec = new EventController();

    private EventController() {}


    public static EventController getInstance( ) {
        return ec;
    }

    public EventTypes.Return createEvent(int userId)
    {
        EventInfo eInfo = new EventInfo();
        Scanner sc = EventInputScanner.getScanner();
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = EventInputScanner.continueOrReset("Create an event");
        if (ret == EventTypes.Return.RESET)
        {
            return EventTypes.Return.RESET;
        }

        String strIn;
        Date dateIn;
        int intIn;
        float fltIn;
        SimpleDateFormat formatIn;
        try {
            // title
            System.out.println("Input the title of the event");
            strIn=readLine(sc);
            eInfo.seteTitle(strIn);

            // location
            System.out.println("Input the event location");
            strIn=readLine(sc);
            eInfo.seteLocation(strIn);

            //start date
            System.out.println("Input the start date of the event as MM/dd/yy - HH:mm");
            strIn=readLine(sc);
            Date start = new SimpleDateFormat("MM/dd/yy - HH:mm").parse(strIn);
            eInfo.seteStart(strIn);

            // end date
            Date end = new SimpleDateFormat("MM/dd/yy - HH:MM").parse("01/01/70 - 00:00");
            while(end.compareTo(start) <= 0) {
                System.out.println("Input the end date of the event after start date as MM/dd/yy - HH:mm");
                strIn = readLine(sc);
                end = new SimpleDateFormat("MM/dd/yy - HH:mm").parse(strIn);
            }

            eInfo.seteEnd(strIn);


            //event description
            System.out.println("Input the event description");
            strIn=readLine(sc);
            eInfo.seteDescription(strIn);

            //organizer id
            eInfo.seteOrgId(userId);


            System.out.println("Input the event available spots");
            intIn=Integer.parseInt(readLine(sc));
            eInfo.seteAvailable(intIn);

            System.out.println("Input the event price in USD");
            fltIn=Float.parseFloat(readLine(sc));
            eInfo.setePrice(fltIn);

            ret = eInfo.saveEvent();

        }catch (Exception e)
        {
            System.out.println(e.toString());
            return EventTypes.Return.GENERALERROR;
        }


        return ret;
    }

    public EventTypes.Return updateEvent(int userId) {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        Scanner sc = EventInputScanner.getScanner();

        ArrayList<EventInfo> eList = null;

        try {

            eList = DBEventInfo.getEventsInfoByEventOrganizer(userId);


            if (eList.size() == 0) {
                System.out.println("You don't have any events to display");
                return EventTypes.Return.SUCCESS;
            } else {
                for (int i = 0; i < eList.size(); i++) {
                    System.out.println(i + " - " + eList.get(i).geteTitle());
                }
            }

            ret = EventInputScanner.continueOrReset("Update an event");
            if (ret == EventTypes.Return.CONTINUE) {
                System.out.println("Please specify the event number");

                int ind = Integer.parseInt(readLine(sc));

                ret = EventInputScanner.continueOrReset("Update event " + eList.get(ind).geteTitle());

                if (ret == EventTypes.Return.CONTINUE) {
                    EventInfo eventInfo = null;

                    eventInfo = editEvent(eList.get(ind), sc);
                    ret = DBEventInfo.updateEvent(eventInfo);
                    if (ret == EventTypes.Return.UPDATEFAILED) {
                        return EventTypes.Return.GENERALERROR;
                    }
                }

                return updateEvent(userId);



            } else {
                return EventTypes.Return.SUCCESS;
            }
        }
        catch(Exception e1)
        {
            return updateEvent(userId);
        }

    }


    public EventTypes.Return deleteEvent(int userId) {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        Scanner sc = EventInputScanner.getScanner();

        ArrayList<EventInfo> eList = null;

        try {
            eList = DBEventInfo.getEventsInfoByEventOrganizer(userId);


            if (eList.size() == 0) {
                System.out.println("You don't have any events to display");
                return EventTypes.Return.SUCCESS;
            } else {
                for (int i = 0; i < eList.size(); i++) {
                    System.out.println(i + " - " + eList.get(i).geteTitle());
                }
            }


            ret = EventInputScanner.continueOrReset("Delete an event");
            if (ret == EventTypes.Return.CONTINUE) {
                System.out.println("Please specify the event number");

                int ind = Integer.parseInt(readLine(sc));

                System.out.println(eList.get(ind).toStringForJoiner());

                ret = EventInputScanner.continueOrReset("Delete event " + eList.get(ind).geteTitle());

                if (ret == EventTypes.Return.CONTINUE) {
                    ret = DBEventInfo.deleteEvent(eList.get(ind));
                    if (ret == EventTypes.Return.DELETEFAILED) {
                        return EventTypes.Return.GENERALERROR;
                    }
                }

                return deleteEvent(userId);
            }
            else
            {
                return EventTypes.Return.SUCCESS;
            }
    }
    catch (Exception e1)
    {
        return deleteEvent(userId);
    }

}

    private String readLine(Scanner sc) throws EmptyStringException
    {
        String strIn=sc.nextLine();
        EmptyStringException e = new EmptyStringException();
        if(strIn.isEmpty() || strIn.equals(""))
        {
            throw e;
        }
        return strIn;
    }

    private EventInfo editEvent(EventInfo eventInfo,Scanner sc){
        EventInfo ei = eventInfo;

        System.out.println(eventInfo.toStringForOrganizer());

        System.out.println("Input the information number you want to change");

        try {
            int ind = Integer.parseInt(readLine(sc));

            System.out.print("Input the new value of ");

            String value;

            switch (ind) {
                case 0:
                    System.out.println("Title");
                    value = readLine(sc);
                    ei.seteTitle(value);
                    break;
                case 1:
                    System.out.println("Location");
                    value = readLine(sc);
                    ei.seteLocation(value);
                    break;
                case 2:
                    System.out.println("Start date as MM/dd/yy - hh:mm");
                    value = readLine(sc);
                    ei.seteStart(value);
                    break;
                case 3:
                    System.out.println("End date as MM/dd/yy - hh:mm");
                    value = readLine(sc);
                    ei.seteEnd(value);
                    break;
                case 4:
                    System.out.println("Price");
                    value = readLine(sc);
                    ei.setePrice(Double.parseDouble(value));
                    break;
                case 5:
                    System.out.println("Available");
                    value = readLine(sc);
                    if (Integer.parseInt(value) >= ei.geteReserved())
                        ei.seteAvailable(Integer.parseInt(value));
                    else
                        System.out.println("It is not possible to set the available spots" +
                                "lower than the reserved spots");
                    break;
                case 6:
                    System.out.println("");
                    System.out.println("It is not possbile to modify this information");
                    break;
                default:
                    System.out.println("not found");
                    throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("you need to provide a valid input");
            ei = editEvent(ei,sc);
            return ei;

        }
        EventTypes.Return ret = EventInputScanner.continueOrReset("Edit another Information");

        if(ret == EventTypes.Return.CONTINUE)
            ei = editEvent(ei,sc);

        return ei;
    }
}

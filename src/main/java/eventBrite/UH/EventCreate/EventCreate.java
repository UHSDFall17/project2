package eventBrite.UH.EventCreate;

import eventBrite.UH.EventTools.EventTypes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EventCreate {



    private static EventCreate ec = new EventCreate();

    private EventCreate() {}


    public static EventCreate getInstance( ) {
        return ec;
    }

    public EventTypes.Return createEvent()
    {
        EventInfo eInfo = new EventInfo();
        Scanner sc = new Scanner(System.in);

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
            System.out.println("Input the start date of the event MM/dd/yy - HH:mm");
            strIn=readLine(sc);
            eInfo.seteStart(strIn);

            // end date
            System.out.println("Input the end date of the event MM/dd/yy - HH:mm");
            strIn=readLine(sc);
            eInfo.seteEnd(strIn);

            //event description
            System.out.println("Input the event description");
            strIn=readLine(sc);
            eInfo.seteDescription(strIn);

            //organizer name
            System.out.println("Input the organizer name");
            strIn=readLine(sc);
            eInfo.seteOrgName(strIn);


            System.out.println("Input the event available spots (-1 means unlimited spots)");
            intIn=Integer.parseInt(readLine(sc));
            eInfo.seteAvailable(intIn);

            System.out.println("Input the event price in USD");
            fltIn=Float.parseFloat(readLine(sc));
            eInfo.setePrice(fltIn);

            eInfo.saveEvent();

        }catch (Exception e)
        {
            System.out.println(e.toString());
            return EventTypes.Return.GENERALERROR;
        }


        return EventTypes.Return.SUCCESS;
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
}

package eventBrite.UH.EventManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventBrite.UH.AccountManager.UserInfo;
import eventBrite.UH.DatabaseManager.DBEventInfo;
import eventBrite.UH.DatabaseManager.DBUserInfo;
import eventBrite.UH.EventTools.AttributesGetter;
import eventBrite.UH.EventTools.EventTypes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class EventInfo implements AttributesGetter {

    private int id;
    private String eTitle;
    private String eLocation;
    private Date eStart;
    private Date eEnd;
    private String eDescription;
    private int eOrgId;
    private double ePrice;
    private int eAvailable;
    private int eReserved;

    public EventInfo()
    {
        id = -1;
        eTitle = "";
        eLocation = "";
        eStart = new Date();
        eEnd = new Date();
        eDescription = "";
        eOrgId = -1;
        eAvailable = 0;
        ePrice = 0;
        eReserved = 0;
    }

    public EventTypes.Return SetEventInfos(
            int id,
            String eTitle,
            String eLocation,
            String eStart,
            String eEnd,
            String eDescription,
            int eOrgId,
            double ePrice,
            int eAvailable,
            int eReserved)
    {
        this.id = id;
        this.eTitle = eTitle;
        this.eLocation = eLocation;
        try {
            this.eStart = new SimpleDateFormat("MM/dd/yy - HH:mm").parse(eStart);
            this.eEnd = new SimpleDateFormat("MM/dd/yy - HH:mm").parse(eEnd);
        } catch (ParseException e) {
            return EventTypes.Return.GENERALERROR;
        }

        this.eDescription = eDescription;
        this.eOrgId = eOrgId;
        this.ePrice = ePrice;
        this.eAvailable = eAvailable;
        this.eReserved = eReserved;

        return EventTypes.Return.SUCCESS;
    }

    public EventTypes.Return saveEvent() throws Exception
    {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = DBEventInfo.insertEventIntoDB(this);

        return ret;
    }

    public void seteTitle(String eTitle) {
        this.eTitle = eTitle;
    }

    public void seteLocation(String eLocation) {
        this.eLocation = eLocation;
    }

    public void seteStart(String eStart) throws ParseException {

        SimpleDateFormat formatIn = new SimpleDateFormat("MM/dd/yy - HH:mm");

        Date dateIn = formatIn.parse(eStart);
        this.eStart = dateIn;
    }

    public void seteEnd(String eEnd) throws ParseException {
        SimpleDateFormat formatIn = new SimpleDateFormat("MM/dd/yy - HH:mm");

        Date dateIn = formatIn.parse(eEnd);
        this.eEnd = dateIn;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public void seteOrgId(int eOrgId) {
        this.eOrgId = eOrgId;
    }

    public void seteAvailable(int eAvailable) {
        this.eAvailable = eAvailable;
    }

    public void addeReserved() {
        this.eReserved ++;
    }

    public void subeReserved() {
        this.eReserved --;
    }

    public void setePrice(double ePrice) {
        this.ePrice = ePrice;
    }

    public void addeReserved(int quantity) {
        if(quantity >= 1)
            this.eReserved += quantity;
    }

    public Object getByName(String name)
    {
        if(name.equals("id"))
        {
            return geteId();
        }
        else if(name.equals("eTitle"))
        {
            return geteTitle();
        }
        else if (name.equals("eLocation"))
        {
            return geteLocation();

        }
        else if (name.equals("eStart"))
        {
            return geteStartForDB();
        }
        else if (name.equals("eEnd"))
        {
            return geteEndForDB();

        }
        else if (name.equals("eOrgId"))
        {
            return geteOrgId();
        }
        else if (name.equals("eDescription"))
        {
            return geteDescription();

        }
        else if (name.equals("ePrice"))
        {
            return getePrice();
        }
        else if (name.equals("eAvailable"))
        {
            return geteAvailable();

        }
        else if (name.equals("eReserved"))
        {
            return geteReserved();
        }
        else
        {
            System.out.println("attribute not found");
            return null;
        }
    }

    public int geteId() {return id;}

    public String geteTitle() {
        return eTitle;
    }

    public String geteLocation() {
        return eLocation;
    }

    public String geteStart() {
        return new SimpleDateFormat("MM/dd/yy - HH:mm").format(eStart);
    }

    public String geteEnd() {
        return new SimpleDateFormat("MM/dd/yy - HH:mm").format(eEnd);
    }

    public String geteStartForDB() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(eStart);
    }

    public String geteEndForDB() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(eEnd);
    }

    public String geteDescription() {
        return eDescription;
    }

    public int geteOrgId() {
        return eOrgId;
    }

    public double getePrice() {
        return ePrice;
    }

    public int geteAvailable() {
        return eAvailable;
    }

    public int geteReserved() {
        return eReserved;
    }

    public String toStringForOrganizer(){
        String info = "";

        info = info + "0 - Title : " + eTitle + "\n";
        info = info + "1 - Location : " + eLocation + "\n";
        info = info + "2 - Start date : " + geteStart() + "\n";
        info = info + "3 - End date : " + geteEnd() + "\n";
        info = info + "4 - Price : " + ePrice + "\n";
        info = info + "5 - Available : " + eAvailable + "\n";
        info = info + "6 - Reserved : " + eReserved + "\n";

        return info;

    }

    public String toStringForJoiner() throws SQLException, ClassNotFoundException {
        String info = "";

        UserInfo ui= null;
        ui = DBUserInfo.getUserInfoByUserId(eOrgId);
        info = info + "Title : " + eTitle + "\n";
        info = info + "Location : " + eLocation + "\n";
        info = info + "Start date : " + geteStart() + "\n";
        info = info + "End date : " + geteEnd() + "\n";
        info = info + "Organizer Name : " + ui.getFirstname() + " " + ui.getLastname() + "\n";
        info = info + "Price : " + ePrice + "\n";
        info = info + "Remaining spots : " + Math.max(0,eAvailable-eReserved) + "\n";

        return info;


    }
}

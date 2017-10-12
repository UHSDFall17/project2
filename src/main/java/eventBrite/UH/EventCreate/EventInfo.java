package eventBrite.UH.EventCreate;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventBrite.UH.EventTools.EventTypes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class EventInfo {


    private String eTitle;
    private String eLocation;
    private Date eStart;
    private Date eEnd;
    private String eDescription;
    private String eOrgName;
    private String eOrgDesc;
    private float ePrice;
    private int eAvailable;
    private int eReserved;

    public EventInfo()
    {
        eTitle = "";
        eLocation = "";
        eStart = new Date();
        eEnd = new Date();
        eDescription = "";
        eOrgName = "";
        eOrgDesc = "";
        eAvailable = 0;
        ePrice = 0;
        eReserved = 0;
    }

    public int SetEventInfos(
            String eTitle,
            String eLocation,
            Date eStart,
            Date eEnd,
            String eDescription,
            String eOrgName,
            String eOrgDesc,
            int eAvailable,
            int eReserved)
    {
        this.eTitle = eTitle;
        this.eLocation = eLocation;
        this.eStart = eStart;
        this.eEnd = eEnd;
        this.eDescription = eDescription;
        this.eOrgName = eOrgName;
        this.eOrgDesc = eOrgDesc;
        this.eAvailable = eAvailable;
        this.eReserved = eReserved;

        return 1;
    }

    public EventTypes.Return saveEvent() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        final String json = mapper.writeValueAsString(this);

        //Object to JSON in file
        //mapper.writeValue(new File("./db.json"),this );
        Files.write(new File("db.json").toPath(), Arrays.asList(json), StandardOpenOption.APPEND);

        //Object to JSON in String
        //String jsonInString = mapper.writeValueAsString(obj);

        return EventTypes.Return.SUCCESS;
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

    public void seteOrgName(String eOrgName) {
        this.eOrgName = eOrgName;
    }

    public void seteOrgDesc(String eOrgDesc) {
        this.eOrgDesc = eOrgDesc;
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

    public void setePrice(float ePrice) {
        this.ePrice = ePrice;
    }

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

    public String geteDescription() {
        return eDescription;
    }

    public String geteOrgName() {
        return eOrgName;
    }

    public String geteOrgDesc() {
        return eOrgDesc;
    }

    public float getePrice() {
        return ePrice;
    }

    public int geteAvailable() {
        return eAvailable;
    }

    public int geteReserved() {
        return eReserved;
    }
}

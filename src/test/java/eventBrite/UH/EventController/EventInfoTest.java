package eventBrite.UH.EventManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventBrite.UH.EventTools.EventTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class EventInfoTest {

    private EventInfo eventInfo;

    @Before
    public void setUp()
    {
        eventInfo = new EventInfo();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testSetEventInfo() throws Exception
    {
        EventTypes.Return ret;
        ret = eventInfo.SetEventInfos(1,"event1","UH","01/02/17 - 10:00",
                "01/03/17 - 10:00","this is event1",1,7,
                20,0);
        assertEquals(EventTypes.Return.SUCCESS, ret);
    }

    @Test
    public void testSetEventInfoWrongDateFormat() throws Exception
    {
        EventTypes.Return ret;
        ret = eventInfo.SetEventInfos(1,"event1","UH","01-02-17 - 10:00",
                "01/03/17 - 10:00","this is event1",1,7,
                20,0);
        assertEquals(EventTypes.Return.GENERALERROR, ret);
    }

    @Test
    public void testGetByNameWrongAttribute()
    {
        Object ret;
        ret = eventInfo.getByName("test");
        assertEquals(null, ret);
    }

    @Test
    public void TestSeteTitle() {

        String sref = "event2";
        eventInfo.seteTitle(sref);
        String stest = eventInfo.geteTitle();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteLocation() {
        String sref = "UH2";
        eventInfo.seteLocation(sref);
        String stest = eventInfo.geteLocation();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteStart() throws ParseException{
        String sref = "01/04/17 - 10:00";
        eventInfo.seteStart(sref);
        String stest = eventInfo.geteStart();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteEnd() throws ParseException {
        String sref = "01/05/17 - 10:00";
        eventInfo.seteEnd(sref);
        String stest = eventInfo.geteEnd();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteDescription() {
        String sref = "event2 desc";
        eventInfo.seteDescription(sref);
        String stest = eventInfo.geteDescription();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteOrgName() {
        int sref = 1;
        eventInfo.seteOrgId(sref);
        int stest = eventInfo.geteOrgId();
        assertEquals(sref, stest);
    }

    @Test
    public void TestSeteAvailable() {
        int sref = 30;
        eventInfo.seteAvailable(sref);
        int stest = eventInfo.geteAvailable();
        assertEquals(sref, stest);
    }

    @Test
    public void TestAddeReserved() {
        int sref = eventInfo.geteReserved();
        eventInfo.addeReserved();
        int stest = eventInfo.geteReserved();
        assertEquals(sref, stest-1);
    }

    @Test
    public void TestSubeReserved() {
        int sref = eventInfo.geteReserved();
        eventInfo.subeReserved();
        int stest = eventInfo.geteReserved();
        assertEquals(sref, stest+1);
    }

    @Test
    public void TestSetePrice() {
        float sref = 30;
        eventInfo.setePrice(sref);
        double stest = eventInfo.getePrice();
        assertEquals(sref, stest,0.001);
    }

    @Test
    public void TestGeteTitle() {

        String etest = eventInfo.geteTitle();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteLocation() {
        String etest = eventInfo.geteLocation();
        assertNotNull(etest);
    }

    @Test
    public void TestGteStart() {
        String etest = eventInfo.geteStart();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteEnd() {
        String etest = eventInfo.geteEnd();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteDescription() {
        String etest = eventInfo.geteDescription();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteOrgName() {
        int etest = eventInfo.geteOrgId();
        assertNotNull(etest);
    }


    @Test
    public void TestGetePrice() {
        double etest = eventInfo.getePrice();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteAvailable() {
        int etest = eventInfo.geteAvailable();
        assertNotNull(etest);
    }

    @Test
    public void TestGeteReserved() {
        int etest = eventInfo.geteReserved();
        assertNotNull(etest);
    }


    @Test
    public void testSaveEvent() throws Exception
    {
        EventTypes.Return ret = EventTypes.Return.SUCCESS;
        ret = eventInfo.SetEventInfos(-1,"event1","UH","01/02/17 - 10:00",
                "01/03/17 - 10:00","this is event1",1,7,
                20,0);
        ret = eventInfo.saveEvent();

        assertEquals(EventTypes.Return.SUCCESS, ret);
    }


}

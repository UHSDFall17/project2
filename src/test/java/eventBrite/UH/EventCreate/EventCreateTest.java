package eventBrite.UH.EventCreate;

import eventBrite.UH.EventTools.EventTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class EventCreateTest {

    private EventCreate eventCreate;
    @Before
    public void setUp() throws FileNotFoundException {
        eventCreate = new EventCreate(new Scanner(new File("./testEventCreate")));
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void TestcreateEvent()
    {
        EventTypes.Return ret = eventCreate.createEvent();
        assertEquals(EventTypes.Return.SUCCESS,ret);

    }
    @Test(expected = java.lang.AssertionError.class)
    public void TestcreateEventAssertException() throws FileNotFoundException {
        eventCreate = new EventCreate(new Scanner(new File("./testEventCreate2")));
        EventTypes.Return ret = eventCreate.createEvent();
        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void TestcreateEventEmptyStringException() throws FileNotFoundException {
        eventCreate = new EventCreate(new Scanner(new File("./testEventCreate3")));
        EventTypes.Return ret = eventCreate.createEvent();
        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }

}

package eventBrite.UH.EventManager;

import eventBrite.UH.EventTools.EventTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class EventCreateTest {

    private static EventCreate eventCreate = EventCreate.getInstance();
    @Before
    public void setUp() throws FileNotFoundException {
        eventCreate = EventCreate.getInstance();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void TestcreateEvent()
    {
        EventTypes.Return ret = null;
        try {
            ret = eventCreate.createEvent(new FileInputStream("./src/test/testInputs/testEventCreate"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test(expected = java.lang.AssertionError.class)
    public void TestcreateEventAssertException() throws FileNotFoundException {
        EventTypes.Return ret = eventCreate.createEvent(new FileInputStream("./src/test/testInputs/testEventCreate2"));
        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void TestcreateEventEmptyStringException() throws FileNotFoundException {
        EventTypes.Return ret = eventCreate.createEvent(new FileInputStream("./src/test/testInputs/testEventCreate3"));
        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }

}

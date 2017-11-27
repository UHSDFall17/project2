package eventBrite.UH.EventManager;

import eventBrite.UH.EventTools.EventInputScanner;
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
    public void TestcreateEvent() throws FileNotFoundException {
        EventTypes.Return ret = null;

        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventCreate"));
        EventInputScanner.setScanner(scanner);

        ret = eventCreate.createEvent(1);


        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test(expected = java.lang.AssertionError.class)
    public void TestcreateEventAssertException() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventCreate2"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventCreate.createEvent(1);
        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void TestcreateEventEmptyStringException() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventCreate3"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventCreate.createEvent(1);
        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }

}

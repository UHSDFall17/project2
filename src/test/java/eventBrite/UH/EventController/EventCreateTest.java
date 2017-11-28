package eventBrite.UH.EventManager;

import eventBrite.UH.DatabaseManager.DatabaseHandler;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EventCreateTest {

    private static EventController eventController = EventController.getInstance();
    @Before
    public void setUp() throws FileNotFoundException {
        eventController = EventController.getInstance();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void TestcreateEvent() throws FileNotFoundException {
        EventTypes.Return ret = null;
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");

        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventCreate/testEventCreate"));
        EventInputScanner.setScanner(scanner);

        ret = eventController.createEvent(1);


        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test
    public void TestcreateEventWrongDB() throws FileNotFoundException {
        EventTypes.Return ret = null;
        DatabaseHandler.setDB("fakedb");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventCreate/testEventCreate"));
        EventInputScanner.setScanner(scanner);

        ret = eventController.createEvent(1);


        assertNotEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test(expected = java.lang.AssertionError.class)
    public void TestcreateEventAssertException() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventCreate/testEventCreate2"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.createEvent(1);
        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void TestcreateEventEmptyStringException() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");

        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventCreate/testEventCreate3"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.createEvent(1);
        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }

}

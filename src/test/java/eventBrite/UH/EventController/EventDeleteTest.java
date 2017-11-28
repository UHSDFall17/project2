package eventBrite.UH.EventManager;

import eventBrite.UH.DatabaseManager.DatabaseHandler;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class EventDeleteTest {

    private static EventController eventController = EventController.getInstance();
    @Before
    public void setUp()
    {
        eventController = EventController.getInstance();
    }

    @Test
    public void TestDeleteEvent() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventDelete/testEventDelete1"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.deleteEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test
    public void TestDeleteEmptyEventNumber() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventDelete/testEventDelete2"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.deleteEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test
    public void TestDeleteEmptyList() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventDelete/testEventDelete2"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.deleteEvent(10000);

        assertEquals(EventTypes.Return.SUCCESS,ret);

    }

    @Test
    public void deleteEventWrongDb() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("tester");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventDelete/testEventDelete1"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.deleteEvent(1);

        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }
}

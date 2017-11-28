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

public class EventUpdateTest {

    private static EventController eventController = EventController.getInstance();
    @Before
    public void setUp()
    {
        eventController = EventController.getInstance();
    }


    @Test
    public void updateEventTitleTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate1"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventLocationTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate2"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventStartDateTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate3"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventEndDateTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate4"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventPriceTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate5"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventAvailableTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate6"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventReservedTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate7"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventEmptyEventNumberTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate8"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventEmptyEventListTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate1"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(100000);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventUnvalidInputTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate9"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventAvailableUnauthorizedTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate10"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventEditAnotherParamTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate11"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventWrongParamNumberTest() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate12"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void updateEventTitleTestWrongDb() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("tester");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventUpdate/testEventUpdate1"));

        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = eventController.updateEvent(1);

        assertEquals(EventTypes.Return.GENERALERROR,ret);
    }
}

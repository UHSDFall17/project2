package eventBrite.UH.EventSearch;

import eventBrite.UH.DatabaseManager.DatabaseHandler;
import eventBrite.UH.EventManager.EventSearch;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class EventSearchTest {

    EventSearch es;

    @Before
    public void setUp()
    {
        es = EventSearch.getInstance();

    }

    @Test
    public void searchEventTestSearchOnly() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch1"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestSearchAgain() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch2"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestSearchByTitle() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch3"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestSearchByTitleNotFound() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch6"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestSearchByLocation() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch4"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS, ret);
    }

    @Test
    public void searchEventTestSearchByLocationNotFound() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch7"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS, ret);
    }

    @Test
    public void searchEventTestSearchByPriceRange() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch5"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestSearchByPriceRangeNotFound() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch8"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventTestExitSearch() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch9"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }

    @Test
    public void searchEventEmptyNumber() throws FileNotFoundException {
        DatabaseHandler.setDB("project2");
        DatabaseHandler.setUser("root");
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsEventSearch/testEventSearch10"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.SUCCESS,ret);
    }



}

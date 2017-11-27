package eventBrite.UH.EventSearch;

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
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch1"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestSearchAgain() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch2"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestSearchByTitle() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch3"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestSearchByTitleNotFound() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch6"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestSearchByLocation() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch4"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET, ret);
    }

    @Test
    public void searchEventTestSearchByLocationNotFound() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch7"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET, ret);
    }

    @Test
    public void searchEventTestSearchByPriceRange() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch5"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestSearchByPriceRangeNotFound() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch8"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }

    @Test
    public void searchEventTestExitSearch() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testEventSearch9"));
        EventInputScanner.setScanner(scanner);

        EventTypes.Return ret = EventTypes.Return.SUCCESS;

        ret = es.searchEvent();

        assertEquals(EventTypes.Return.RESET,ret);
    }



}

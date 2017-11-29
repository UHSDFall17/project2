package eventBrite.UH.StaticClass;

import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes;
import org.junit.Test;

public class EventToolsAllocationTest {

    @Test
    public void testEventInputScannerAllocation()
    {
        EventInputScanner eis = new EventInputScanner();
    }

    @Test
    public void testEventTypesAllocation()
    {
        EventTypes et = new EventTypes();
    }
}

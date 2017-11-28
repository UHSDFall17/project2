package eventBrite.UH.DatabaseManager;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class DatabaseTest {

    @Test
    public void testDatabaseHandlerAllocation()
    {
        DatabaseHandler dh = new DatabaseHandler();
        assertNotEquals(null,dh);
    }

    @Test
    public void testDBEventInfoAllocation()
    {
        DBEventInfo dbei = new DBEventInfo();
        assertNotEquals(null,dbei);
    }

    @Test
    public void testDBUserInfoAllocation()
    {
        DBUserInfo dbui = new DBUserInfo();
        assertNotEquals(null,dbui);
    }
}

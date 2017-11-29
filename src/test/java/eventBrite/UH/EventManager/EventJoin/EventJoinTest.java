package eventBrite.UH.EventManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

import eventBrite.UH.EventApp.TestUtility;
import eventBrite.UH.EventTools.EventTypes.Return;

public class EventJoinTest
{
	private EventJoin eventJoin = EventJoin.getInstance();
	private static TestUtility.FunctionToTest joinEvent = 
		(ArrayList<Object> list) -> EventJoin.getInstance().joinEvent((EventInfo)(list.get(0)));
	private static ArrayList<Object> paramList;

	@Before
	public void setUp() {}

	@After
	public void tearDown() {}

	@Test
	public void joinEvent() throws Exception
	{
		EventInfo eventInfo = null;
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		String expectedRet = Return.EEMAILFORMAT.toString();
		TestUtility.testFunctionOutput("Y\nA\nB\nA\nA\n1\nn", expectedRet, joinEvent, paramList);
	}

}
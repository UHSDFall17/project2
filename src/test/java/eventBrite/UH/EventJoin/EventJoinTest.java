package eventBrite.UH.EventManager;

import eventBrite.UH.DatabaseManager.DBEventInfo;
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
	public void testEventJoinWrongEmailFormat() throws Exception
	{
		EventInfo eventInfo = null;
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		String expectedRet = Return.EEMAILFORMAT.toString();
		TestUtility.testFunctionOutputError("Y\nA\nB\nA\nA\n1\nn", expectedRet, joinEvent, paramList);
	}

	// There is a lot of test that we can add later

	@Test
	public void testEventJoinNoEventFound() throws Exception
	{
		EventInfo eventInfo = null;
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		String expectedRet = Return.ENOEVENT.toString();
		TestUtility.testFunctionOutputError("Y\nA\nB\nX@X.com\nX@X.com\n1234561234\ny\n1", 
			expectedRet, joinEvent, paramList);
	}

	@Test
	public void testEventJoinNumberTicketNotInt() throws Exception
	{
		EventInfo eventInfo = null;
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		String expectedRet = Return.ENOEVENT.toString();
		TestUtility.testFunctionOutputError("Y\nA\nB\nX@X.com\nX@X.com\n1234561234\ny\nss\n1", 
			expectedRet, joinEvent, paramList);
	}

	@Test
	public void testEventJoinNoAvailableTicket() throws Exception
	{
		EventInfo eventInfo = DBEventInfo.getEventsInfoByEventTitle("event3").get(0);
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		String expectedRet = Return.RESET.toString();
		TestUtility.testFunctionOutputError("Y\nA\nB\nX@X.com\nX@X.com\n1234561234\ny\n80\nn",
			expectedRet, joinEvent, paramList);
	}

	@Test
	public void testEventJoinNoAvailableTicketThanChangeTicketNumber() throws Exception
	{
		EventInfo eventInfo = DBEventInfo.getEventsInfoByEventTitle("event3").get(0);
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		Return expectedRet = Return.RESET;
		TestUtility.testFunctionReturn("Y\nA\nB\nX@X.com\nX@X.com\n1234561234\ny\n80\ny\n1\nn",
			expectedRet, joinEvent, paramList);
	}

	@Test
	public void testEventJoinWithoutSubmit() throws Exception
	{
		EventInfo eventInfo = DBEventInfo.getEventsInfoByEventTitle("event3").get(0);
		paramList = new ArrayList<Object>(Arrays.asList(eventInfo));
		Return expectedRet = Return.RESET;
		TestUtility.testFunctionReturn("Y\nA\nB\nX@X.com\nX@X.com\n1234561234\nn\nn",
			expectedRet, joinEvent, paramList);
	}
}
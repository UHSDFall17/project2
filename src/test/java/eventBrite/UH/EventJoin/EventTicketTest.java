package eventBrite.UH.EventManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import eventBrite.UH.EventTools.EventTypes.TicketType;


public class EventTicketTest 
{
	private EventTicket 	ticket;
	
	@Before
	public void setUp()
	{
	}

	@After
	public void tearDown() 
	{
	}

	@Test
	public void testPendingTicket()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 2, 25.99f);
		boolean boolActualValue = ticket.isApproved();
		boolean boolExpectedValue = false;
		assertEquals(boolExpectedValue, boolActualValue);
	}

	@Test
	public void testApprovedTicket()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 2, 25.99f);
		ticket.approveTicket();
		boolean boolActualValue = ticket.isApproved();
		boolean boolExpectedValue = true;
		assertEquals(boolExpectedValue, boolActualValue);
	}

	@Test
	public void testDefaultTicketQuantity()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, -5, 25.99f);
		int actualValue = ticket.getTicketQuantity();
		int expectedValue = 1;
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testTicketQuantity()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 4, 25.99f);
		int actualValue = ticket.getTicketQuantity();
		int expectedValue = 4;
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testDefaultTicketCover()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 4, -24.65f);
		float actualValue = ticket.getTicketCover();
		float expectedValue = 0f;
		assertEquals(expectedValue, actualValue, 0.1);
	}

	@Test
	public void testTicketCover()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 4, 25.99f);
		float actualValue = ticket.getTicketCover();
		float expectedValue = 25.99f;
		assertEquals(expectedValue, actualValue, 0.1);
	}

	@Test
	public void testClientName()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 4, 25.99f);
		String actualValue = ticket.getClientFullName();
		String expectedValue = "clientFullName";
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testTicketType()
	{
		ticket = new EventTicket("clientFullName", TicketType.VIP, 4, 25.99f);
		TicketType actualValue = ticket.getTicketType();
		TicketType expectedValue = TicketType.VIP;
		assertEquals(expectedValue, actualValue);
	}		
}
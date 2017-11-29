package eventBrite.UH.EventManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import eventBrite.UH.EventTools.EventTypes.TicketType;
import eventBrite.UH.EventTools.EventTypes.Return;

public class RegistrationFormTest
{
	private RegistrationForm registrationForm;
	private Return actualValue;
	private Return expectedValue;

	@Before
	public void setUp()
	{
		registrationForm = new RegistrationForm();
	}

	@After
	public void tearDown()
	{
	}

	@Test
	public void testRightMailFormat()
	{
		expectedValue = Return.SUCCESS;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "952-255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testWrongMailFormat()
	{
		expectedValue = Return.EEMAILFORMAT;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "xxxxx.com", "xxxx@xx.com", "952-255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testWrongMailConfirmation()
	{
		expectedValue = Return.EEMAILMATCH;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "xxxx@xx.com", "xxxx@xx2.com", "952-255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testRightPhoneNumberFormat_TenDigits()
	{
		expectedValue = Return.SUCCESS;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "9522555522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testRightPhoneNumberFormat_DashSeperator()
	{
		expectedValue = Return.SUCCESS;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "952-255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testRightPhoneNumberFormat_ParenthesesDashSeperator()
	{
		expectedValue = Return.SUCCESS;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "(952)255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testWrongPhoneNumberFormat_LessThanTenDigits()
	{
		expectedValue = Return.EPHONEFORMAT;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "952-255-22");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testWrongPhoneNumberFormat_SpaceSeperator()
	{
		expectedValue = Return.EPHONEFORMAT;
		actualValue = registrationForm.setUserRegistrationInfo(
			"XXXX", "YYYY", "x+x_ssd-ddd@dkj.com", "x+x_ssd-ddd@dkj.com", "952 255-5522");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEventTicketCreation_RegistrationInfoNotSet()
	{
		expectedValue = Return.EINCOMPLETEREG;
		actualValue = registrationForm.createEventTicket(TicketType.VIP, 1);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEventTicketCreation_RegistrationInfoSet_EventInfoNotSet()
	{
		expectedValue = Return.ENOEVENT;
		registrationForm.setUserRegistrationInfo(
			"Raafat", "Feki", "fekiraafat@gmail.com", "fekiraafat@gmail.com", "(952)255-5522");
		actualValue = registrationForm.createEventTicket(TicketType.VIP, 1);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEventTicketCreation_RegistrationInfoSet_EventInfoSet_NotAvailableTickets()
	{
		expectedValue = Return.ENOTICKET;
		EventInfo eventInfo = new EventInfo();
		eventInfo.seteAvailable(5);
		eventInfo.addeReserved(4);
		registrationForm.setEventInfo(eventInfo);
		registrationForm.setUserRegistrationInfo(
			"Raafat", "Feki", "fekiraafat@gmail.com", "fekiraafat@gmail.com", "(952)255-5522");
		actualValue = registrationForm.createEventTicket(TicketType.VIP, 2);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEventTicketCreation_RegistrationInfoSet_EventInfoSet()
	{
		expectedValue = Return.SUCCESS;
		EventInfo eventInfo = new EventInfo();
		eventInfo.seteAvailable(5);
		eventInfo.addeReserved(4);
		registrationForm.setEventInfo(eventInfo);
		registrationForm.setUserRegistrationInfo(
			"Raafat", "Feki", "fekiraafat@gmail.com", "fekiraafat@gmail.com", "(952)255-5522");
		actualValue = registrationForm.createEventTicket(TicketType.VIP, 1);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEventTicketCreation_RegistrationInfoSet_ParamConstruct()
	{
		expectedValue = Return.SUCCESS;
		EventInfo eventInfo = new EventInfo();
		eventInfo.seteAvailable(5);
		eventInfo.addeReserved(4);
		registrationForm.setEventInfo(eventInfo);
		RegistrationForm regisForm = new RegistrationForm(eventInfo);
		regisForm.setUserRegistrationInfo(
			"Raafat", "Feki", "fekiraafat@gmail.com", "fekiraafat@gmail.com", "(952)255-5522");
		actualValue = regisForm.createEventTicket(TicketType.VIP, 1);
		assertEquals(expectedValue, actualValue);
	}
}
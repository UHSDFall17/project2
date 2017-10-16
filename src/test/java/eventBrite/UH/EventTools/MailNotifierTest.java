package eventBrite.UH.EventTools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class MailNotifierTest 
{
	private MailNotifier 	mailNotif;
	private int 			actualValue;
	private int 			expectedValue;
	private boolean 		boolActualValue;
	
	@Before
	public void setUp()
	{
		mailNotif = new MailNotifier();
	}

	@After
	public void tearDown() 
	{
	}

	@Test
	public void testSenderInfoSetting_NotEmailFormat()
	{
		expectedValue = -1;
		actualValue = mailNotif.setSenderInfo("xxxxxx.com", "XXXXX");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testSenderInfoSetting_NotGmail()
	{
		expectedValue = -1;
		actualValue = mailNotif.setSenderInfo("x+x_ssd-ddd@dkj.com", "XXXXX");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testSenderInfoSetting_AcceptedEmailFormat()
	{
		expectedValue = 0;
		actualValue = mailNotif.setSenderInfo("x+x_ssd-ddd@gmail.com", "XXXXX");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEmailInfoSetting_NotEmailFormat()
	{
		expectedValue = -1;
		actualValue = mailNotif.setEmailInfo("xxxxxx.com", "XXXXX", "YYYYYY");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEmailInfoSetting_NotGmail()
	{
		expectedValue = -1;
		actualValue = mailNotif.setEmailInfo("x+x_ssd-ddd@dkj.com", "MailSubject", "MailMsg");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testEmailInfoSetting_AcceptedEmailFormat()
	{
		expectedValue = 0;
		actualValue = mailNotif.setEmailInfo("x+x_ssd-ddd@gmail.com", "MailSubject", "MailMsg");
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testMsgIsReadyToSend_InitialValue()
	{
		boolActualValue = mailNotif.isReadyToSend();
		mailNotif.setSenderInfo("x+x_ssd-ddd@gmail.com", "XXXXX");
		mailNotif.setEmailInfo("x+x_ssd-ddd@gmail.com", "MailSubject", "MailMsg");
		assertEquals(false, boolActualValue);
	}

	@Test
	public void testMsgIsReadyToSend_SenderInfoNotSet()
	{
		mailNotif.setEmailInfo("x+x_ssd-ddd@gmail.com", "MailSubject", "MailMsg");
		boolActualValue = mailNotif.isReadyToSend();
		assertEquals(false, boolActualValue);
	}

	@Test
	public void testMsgIsReadyToSend_MailInfoNotSet()
	{
		boolActualValue = mailNotif.isReadyToSend();
		mailNotif.setSenderInfo("x+x_ssd-ddd@gmail.com", "XXXXX");
		assertEquals(false, boolActualValue);
	}

	@Test
	public void testMsgIsReadyToSend_MailInfoSetBeforeSenderInfo()
	{
		mailNotif.setEmailInfo("x+x_ssd-ddd@gmail.com", "MailSubject", "MailMsg");
		mailNotif.setSenderInfo("x+x_ssd-ddd@gmail.com", "XXXXX");
		boolActualValue = mailNotif.isReadyToSend();
		assertEquals(true, boolActualValue);
	}

	@Test
	public void testMsgIsReadyToSend_SenderInfoSetBeforeMailInfo()
	{
		mailNotif.setSenderInfo("x+x_ssd-ddd@gmail.com", "XXXXX");
		mailNotif.setEmailInfo("x+x_ssd-ddd@gmail.com", "MailSubject", "MailMsg");
		boolActualValue = mailNotif.isReadyToSend();
		assertEquals(true, boolActualValue);
	}

	// Use your gmail and password to test this functionality.
	@Ignore("It needs personal information (Gmail + passwords)") 
	@Test
	public void testMsgIsReadyToSend_SendMail() throws Exception
	{
		mailNotif.setSenderInfo("XXXX@gmail.com", "XXXXX");
		mailNotif.setEmailInfo("XXXXX@gmail.com", "MailSubject", "MailMsg");
		mailNotif.send();
	}			
}   
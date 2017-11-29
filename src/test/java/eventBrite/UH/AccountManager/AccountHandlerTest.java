package eventBrite.UH.AccountManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventApp.TestUtility;
import java.util.ArrayList;

public class AccountHandlerTest {

	private static TestUtility.FunctionToTest loginFunc = 
					(ArrayList<Object> list) -> AccountHandler.getInstance().login();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testLoginNotExistingAccount() throws Exception 
	{
		String expectedRet = Return.EACCOUNTNOTFOUND.toString();
		TestUtility.testFunctionOutput("y\njohn@gmail.com\nazert1234\nN", expectedRet, loginFunc, null);
	}

	@Test
	public void testLoginWrongEmail() throws Exception 
	{
		String expectedRet = Return.EEMAILFORMAT.toString();
		TestUtility.testFunctionOutput("Y\njohnsgmail.com\nazert1234\nN", expectedRet, loginFunc, null);
	}

	@Test
	public void testLoginWrongPassword() throws Exception
	{
		String expectedRet = Return.EWRONGPASSWD.toString();
		TestUtility.testFunctionOutput("y\nnour@cs.uh.edu\nreal1234\nn", expectedRet, loginFunc, null);
	}
}

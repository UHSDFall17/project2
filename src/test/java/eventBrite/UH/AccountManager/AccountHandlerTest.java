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

public class AccountHandlerTest {

	private static 			AccountHandler 	accountHandler 	= AccountHandler.getInstance();
	private static final 	PrintStream 	stdout			= System.out;
	
	public interface FunctionToTest {

		public Object execute();
	}

	private void testFunctionOutput(
		String input, 
		String expectedOutput, 
		FunctionToTest fnct) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		System.setOut(ps);
		EventInputScanner.setScanner(new Scanner(new ByteArrayInputStream(input.getBytes("UTF-8"))));
		fnct.execute();

		assertEquals(expectedOutput, out.toString().split("Occured: ")[1].split("\n")[0]);

		System.out.flush();
		System.setOut(stdout);
		System.out.print(out.toString());
	}

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
		FunctionToTest loginFunc = () -> accountHandler.login();
		testFunctionOutput("y\njohn@gmail.com\nazert1234\nN", expectedRet, loginFunc);
	}

	@Test
	public void testLoginWrongEmail() throws Exception 
	{
		String expectedRet = Return.EEMAILFORMAT.toString();
		FunctionToTest loginFunc = () -> accountHandler.login();
		testFunctionOutput("Y\njohnsgmail.com\nazert1234\nN", expectedRet, loginFunc);
	}

	@Test
	public void testLoginWrongPassword() throws Exception
	{
		String expectedRet = Return.EWRONGPASSWD.toString();
		FunctionToTest loginFunc = () -> accountHandler.login();
		testFunctionOutput("y\nnour@cs.uh.edu\nreal1234\nn", expectedRet, loginFunc);
	}
}

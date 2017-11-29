package eventBrite.UH.EventApp;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

import eventBrite.UH.EventTools.EventInputScanner;
import java.util.ArrayList;

public abstract class TestUtility
{	
	private static final PrintStream stdout = System.out;

	public interface FunctionToTest 
	{
		public Object execute(ArrayList<Object> paramList);
	}

	public static void testFunctionOutput(
		String input, 
		String expectedOutput, 
		FunctionToTest fnct,
		ArrayList<Object> paramList) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		System.setOut(ps);
		EventInputScanner.setScanner(new Scanner(new ByteArrayInputStream(input.getBytes("UTF-8"))));
		fnct.execute(paramList);

		assertEquals(expectedOutput, out.toString().split("Occured: ")[1].split("\n")[0]);

		System.out.flush();
		System.setOut(stdout);
		System.out.print(out.toString());
	}
}

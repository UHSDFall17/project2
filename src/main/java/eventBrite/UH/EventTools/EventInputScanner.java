package eventBrite.UH.EventTools;

import java.util.Scanner;
import eventBrite.UH.EventTools.EventTypes.Return;

public class EventInputScanner  
{
	private static Scanner sc = new Scanner(System.in);;

	public static Return continueOrReset(String featureName)
	{
		System.out.println(featureName + ": [Continue/cancel]");
		String resp = sc.next();

		if(resp.toUpperCase().equals("CANCEL"))
			return Return.RESET;

		if (resp.toUpperCase().equals("CONTINUE"))
			return Return.CONTINUE;

		Return.printError(Return.EWRONGINPUT);
		return continueOrReset(featureName);
	}
}
package eventBrite.UH.EventTools;

import java.util.Scanner;
import eventBrite.UH.EventTools.EventTypes.Return;

public class EventInputScanner  
{
	private static Scanner sc = new Scanner(System.in);

	public static Scanner getScanner() {return sc;}
	public static void setScanner(Scanner newSc) {sc =newSc;}

	public static Return continueOrReset(String featureName)
	{
		System.out.println(featureName + ": Continue ?[y/n]");
		String resp = sc.nextLine();

		if(resp.toUpperCase().equals("N"))
			return Return.RESET;

		if (resp.toUpperCase().equals("Y"))
			return Return.CONTINUE;

		Return.printError(Return.EWRONGINPUT);
		return continueOrReset(featureName);
	}
}

package eventBrite.UH.EventTools;

public class EventTypes
{
	public enum TicketType 
	{
		GA("General Access"),
		VIP("Very Important Person");
		
		private String name = "";

		TicketType(String name)
		{
			this.name = name;
		}

		public String toString()
		{
			return name;
		}
	}

	public enum AccountType 
	{
		GUEST(0),
		MEMBER(1),
		ADMIN(2);
		
		private int code;

		AccountType(int code)
		{
			this.code = code;
		}
	}

	public enum Return
	{
		GENERALERROR		(0, "General Error"),
		SUCCESS 			(1, "Success"),

		// return codes for mail notifier 2-10
		EEMAILFORMAT 		(2, "Wrong Email Format"),
		EGMAILFORMAT 		(3, "Wrong Gmail Format"),
		EEMAILMATCH 		(4, "Mails don't match"),
		// return codes for user account 11-20
		EPASSWDMATCH 		(11, "Passwords don't match"),
		EWRONGPASSWD 		(12, "Wrong Password"),
		EACCOUNTNOTFOUND 	(13, "Account Does NOT exist"),
		// return codes for database handler 21-30
		CONNECTIONFAILED	(21, "Connection failed"),
		SELECTFAILED		(22, "Select from table failed"),
		EVENTNOTFOUND		(23, "event not found"),
		INSERTFAILED		(24, "insert into table failed"),
		UPDATEFAILED		(25, "update table failed"),
		DELETEFAILED		(26, "delete from table failed"),
		CLOSEFAILED			(27, "closing db failed"),
		// return codes for signals 31-50
		RESET				(31, "Reload Main Page"),
		CONTINUE			(32, "Continue"),
		EXIT 				(32, "Exit Application"),
		//return codes for search 51-60
		EMPTYLIST			(51, "No events were found"),
		// return codes for general usage 100:
		EXCEPTIONRAISED 	(101, "Exception Raised"),
		EPHONEFORMAT 		(102, "Wrong Phone Format"),
		EINSUFFPRIV			(103, "Insufficient Privileges error"),
		EWRONGINPUT			(104, "Wrong Input"),
		EINCOMPLETEREG		(105, "Registration Form is not completely Filled"),
		ENOEVENT			(106, "No event was selected"),
		ENOTICKET			(107, "No available tickets");

		private int 	code;
		private String 	description;

		Return(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public int getcode() {return code;}

		public String toString()
		{
			return description;
		}

		public static void printError(Return ret) 
		{
			System.out.println("An Error has Occured: " + ret);
		}
	}
}

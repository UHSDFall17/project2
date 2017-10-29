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
		//return codes for database handler 21-30
		CONNECTIONFAILED	(21, "Connection failed"),
		SELECTFAILED		(22, "Select from table failed"),
		EVENTNOTFOUND		(21, "event not found"),
		// return codes for general usage 100:
		EXCEPTIONRAISED 	(7, "Exception Raised"),
		EPHONEFORMAT 		(5, "Wrong Phone Format");

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
	}
}

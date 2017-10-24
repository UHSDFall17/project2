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
		EEMAILFORMAT 		(2, "Wrong Email Format"),
		EGMAILFORMAT 		(3, "Wrong Gmail Format"),
		EPHONEFORMAT 		(4, "Wrong Phone Format"),
		EEMAILMATCH 		(5, "Mails don't match"),
		EPASSWDMATCH 		(6, "Passwords don't match"),
		EXCEPTIONRAISED 	(7, "Exception Raised");

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

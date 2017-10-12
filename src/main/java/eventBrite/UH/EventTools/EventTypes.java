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
		SUCCESS(1),
		GENERALERROR(0);

		private int code;

		Return(int code) {
			this.code = code;
		}

		public int getcode() {return code;}

	}
}




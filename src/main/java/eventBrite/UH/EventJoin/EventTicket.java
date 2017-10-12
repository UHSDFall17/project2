package eventBrite.UH.EventJoin;

import eventBrite.UH.EventTools.EventTypes.TicketType;

public class EventTicket
{
	private int						ticketQuantity;
	private TicketInfo				ticketInfo;

	public EventTicket(
		String 		clientFullName, 
		TicketType 	ticketType, 
		int 		quantity, 
		float 		newCover)
	{
		ticketInfo = new TicketInfo(clientFullName, ticketType);
		ticketInfo.setTicketCover(newCover);
		ticketQuantity = (quantity > 0) ? quantity : 1;
	}

	// approveTicket should be accessible only to the Event owner/ organizer
	public void 		approveTicket() 	{ticketInfo.isPendingTicket = false;} 
	public boolean 		isApproved() 		{return !ticketInfo.isPendingTicket;}

	public int 			getTicketQuantity() {return ticketQuantity;}
	public float 		getTicketCover() 	{return ticketInfo.ticketCover;}
	public String		getClientFullName()	{return ticketInfo.clientFullName;}
	public TicketType	getTicketType()		{return ticketInfo.ticketType;}

	private static class TicketInfo
	{
		private String 		clientFullName;
		private TicketType 	ticketType;
		private	boolean 	isPendingTicket;
		private float		ticketCover;

		public TicketInfo(String clientFullName, TicketType ticketType)
		{
			this.clientFullName 		= clientFullName;
			this.ticketType 			= ticketType;
			isPendingTicket 			= true;
			ticketCover 				= -1;
		}

		private int setTicketCover(float newCover)
		{
			ticketCover = (newCover > 0) ? newCover : 0;
			return 0;
		}
	}
}

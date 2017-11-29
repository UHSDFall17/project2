package eventBrite.UH.EventManager;

import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventTypes.TicketType;
import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.DatabaseManager.DBEventInfo;

class RegistrationForm  
{
	private UserRegistrationInfo 	registrationInfo;
	private EventTicket				eventTicket;
	private EventInfo				eventInfo;
	private MailNotifier 			eMailNotifier;

	public RegistrationForm()
	{
		registrationInfo = new UserRegistrationInfo();
		eMailNotifier 	 = new MailNotifier();
		eMailNotifier.setSenderInfo("programmincodetest@gmail.com", "Project2");
	}

	public RegistrationForm(EventInfo eventInfo) 
	{
		this();
		this.eventInfo = eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo)
	{
		this.eventInfo = eventInfo;
	}

	public int getAvailableTicket()
	{
		return eventInfo.geteAvailable() - eventInfo.geteReserved();
	}

	public Return createEventTicket(TicketType ticketType, int ticketQuantity)
	{
		if(!registrationInfo.isConfirmed)
			return Return.EINCOMPLETEREG;
		if(eventInfo == null)
			return Return.ENOEVENT;
		if((eventInfo.geteAvailable() - eventInfo.geteReserved() - ticketQuantity) < 0)
			return Return.ENOTICKET;
		String clientFullName = registrationInfo.firstName + " " + registrationInfo.lastName;
		eventTicket = new EventTicket(clientFullName, ticketType,
									  ticketQuantity, eventInfo.getePrice());
		return Return.SUCCESS;
	}

	public Return updateEvent()
	{
		eventInfo.addeReserved(eventTicket.getTicketQuantity());
		return DBEventInfo.updateEvent(eventInfo);
	}

	public Return payTickets()
	{
		if(EventInputScanner.continueOrReset(String.format("Order now: %d Ticket for %.2f$",
			eventTicket.getTicketQuantity(),
			eventTicket.getTicketQuantity() * eventTicket.getTicketCover())) == Return.RESET)
			return Return.RESET;
		// Payment function here
		eventTicket.approveTicket();
		return Return.SUCCESS;
	}

	public void sendConfirmation()
	{
		if(eventTicket.isApproved())
		{
			eMailNotifier.setEmailInfo(registrationInfo.emailAddress, 
				"EventBrite-UH Ticket", getEmailFormatTicket());
			eMailNotifier.send();
		}
	}

	public Return setUserRegistrationInfo(
		String	firstName,
		String	lastName,
		String	emailAddress,
		String	emailConfirm,
		String 	phoneNumber)
	{
		registrationInfo.firstName = firstName;
		registrationInfo.lastName = lastName;
		if(!registrationInfo.setEmailAddress(emailAddress)) return Return.EEMAILFORMAT;
		if(!confirmEmailAddress(emailConfirm))				return Return.EEMAILMATCH;
		if(!registrationInfo.setCellPhone(phoneNumber)) 		return Return.EPHONEFORMAT;
		registrationInfo.isConfirmed = true;

		System.out.format("\nFirstName: %s\nLastName: %s\nEmail: %s\nPhone Number: %s\n",
							firstName, lastName, emailAddress, phoneNumber);

		return Return.SUCCESS;
	}

	private String getEmailFormatTicket()
	{
		if(eventTicket.isApproved())
		{
			return (String.format("Dear %s,\n\nThis is a confirmation to attend the event:"
				+ "\t%s\n\n\tLocation: %s\n\tStart Time: %s\n\tEnd Time: %s\n\tQuantity: %s\n\tType:"
				+ " %s\n\nPlease print a copy of this document or display this e-mail using an electronic"
				+ "device. Ammount Charged %.2f$.\nBest Regard,\n\nEventBrite-UH Community Manager.",
				eventTicket.getClientFullName(), eventInfo.geteTitle(), eventInfo.geteLocation(), eventInfo.geteStart(), 
				eventInfo.geteEnd(), eventTicket.getTicketQuantity(), 
				eventTicket.getTicketType().toString(), 
				eventTicket.getTicketCover()*eventTicket.getTicketQuantity()
				)); 
		}
		return null;
	}

	private boolean confirmEmailAddress(String emailConfirm)
	{
		return registrationInfo.emailAddress.equals(emailConfirm);
	}

	private static class UserRegistrationInfo
	{
		private String	firstName;
		private String	lastName;
		private String	emailAddress;
		private String 	cellPhone;
		private Boolean isConfirmed;
		// birthday Not necessary now

		public UserRegistrationInfo() {isConfirmed = false;}

		private boolean setEmailAddress(String newEmailAddress) 
		{
			boolean ret;
			ret = MailNotifier.checkEmailAddressFormat(newEmailAddress);
			if (ret)
				emailAddress = newEmailAddress;
			return ret;
		}

		private boolean setCellPhone(String newCellPhone) 
		{
			// 1234567890, 123-456-7890, (123)456-7890 or (123)4567890 are the accepted format.
			final String PHONE_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
			if (newCellPhone.matches(PHONE_REGEX)) 
			{
			 	cellPhone = newCellPhone;
			 	return true;
			} 
			return false;
		}
	}
}

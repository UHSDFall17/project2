package eventBrite.UH.EventJoin;

import eventBrite.UH.EventCreate.EventInfo;
import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventTypes.TicketType;

public class RegistrationForm  
{
	private UserRegistrationInfo 	registrationInfo;
	private EventTicket				eventTicket;
	private EventInfo				eventInfo;

	public RegistrationForm() 
	{
		registrationInfo = new UserRegistrationInfo(); 
	}

	public int createEventTicket(TicketType ticketType, int ticketQuantity)
	{
		if(!registrationInfo.isConfirmed)
			return -1;
		String clientFullName = registrationInfo.firstName + " " + registrationInfo.lastName;
		eventTicket = new EventTicket(clientFullName, ticketType,
									  ticketQuantity, eventInfo.getePrice());
		return 0;
	}

	public int setUserRegistrationInfo(
		String	firstName,
		String	lastName,
		String	emailAddress,
		String	emailConfirm,
		String 	cellPhone)
	{
		registrationInfo.firstName = firstName;
		registrationInfo.lastName = lastName;
		if(!registrationInfo.setEmailAddress(emailAddress)) return -1;
		if(!confirmEmailAddress(emailConfirm))				return -2;
		if(!registrationInfo.setCellPhone(cellPhone)) 		return -3;
		registrationInfo.isConfirmed = true;
		return 0;
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
			// 1234567890, 123-456-7890, (123)456-7890 or (123)4567890 are the accepted format
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

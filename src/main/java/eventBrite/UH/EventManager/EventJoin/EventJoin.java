package eventBrite.UH.EventManager;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.AccountManager.UserInfo;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.EventTypes.TicketType;
import eventBrite.UH.EventApp.Main;

import java.util.Scanner;

class EventJoin
{
	private static EventJoin eventJoin = new EventJoin();

	private EventJoin() {}
	
	public static EventJoin getInstance() {return eventJoin;}
	
	public Return joinEvent(EventInfo eventInfo)
	{
		UserInfo userInfo = null;
		if(Main.getUserAccount() != null)
			userInfo = Main.getUserAccount().getUserInfo();
		RegistrationForm registrationForm = new RegistrationForm(eventInfo);
		Return ret = fillRegistrationForm(registrationForm, userInfo);

		if(ret != Return.SUCCESS)
			return ret;

		ret = buyTickets(registrationForm);

		if(ret != Return.SUCCESS)
		{
			Return.printError(ret);
			return Return.RESET;
		}

		if(registrationForm.payTickets() == Return.RESET)
			return Return.RESET;

		if(registrationForm.updateEvent() == Return.SUCCESS)
		{
			System.out.println("Ticket purchase is Successful\n"
							 + " You will receive your tickets by e-mail.");
			registrationForm.sendConfirmation();
		}
		return Return.SUCCESS;
	}

	private Return buyTickets(RegistrationForm registrationForm)
	{
		int quantity;
		Scanner sc = EventInputScanner.getScanner();
		
		System.out.println("Choose the number of tickets you want to purchase:");
		try
		{
			quantity = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e)
		{
			System.out.println(e);
			return buyTickets(registrationForm);
		}

		/* The user should input the type of the ticket. 
		 * However, the handeling of events ticket type is not implemented yet.  
		 */

		Return ret = registrationForm.createEventTicket(TicketType.GA, quantity);

		if(ret == Return.ENOTICKET)
		{
			System.out.format("There is only %s remaining ticket(s).\n", 
				registrationForm.getAvailableTicket());
			if(EventInputScanner.continueOrReset("Choose another number") == Return.CONTINUE)
				return buyTickets(registrationForm);
			else
				return Return.RESET;
		}

		return ret;
	}

	private Return fillRegistrationForm(RegistrationForm registrationForm, UserInfo userInfo)
	{
		Return ret = loadRegistrationPage(registrationForm, userInfo);

		if((ret != Return.SUCCESS) && (ret != Return.RESET))
		{
			Return.printError(ret);
			return fillRegistrationForm(registrationForm, userInfo);
		}

		if(ret == Return.SUCCESS)
		{
			if(EventInputScanner.continueOrReset("Submit") == Return.RESET)
					return fillRegistrationForm(registrationForm, userInfo);
		}

		return ret;
	}

	private Return loadRegistrationPage(RegistrationForm registrationForm, UserInfo userInfo)
	{
		String firstName, lastName, email, emailConfirm, phoneNumber;

		Scanner sc = EventInputScanner.getScanner();
		if(EventInputScanner.continueOrReset("Fill Registration Form") == Return.RESET)
			return Return.RESET;

		if(userInfo == null || !userInfo.isFullySet())
		{
			System.out.println("Enter Your FirstName:");
			firstName = sc.nextLine();
			System.out.println("Enter Your LastName:");
				lastName = sc.nextLine();
			System.out.println("Enter Your Email:");
			email = sc.nextLine();   
		}
		else
		{
			firstName	= userInfo.getFirstname();
			lastName	= userInfo.getLastname();
			email		= userInfo.getEmail();
		}

		System.out.println("Confirm Your Email:");
		emailConfirm = sc.nextLine();     
		System.out.println("Enter Your Phone Number:");
		System.out.println("\tThese are the acceptable format: 1234567890,"
						 + "123-456-7890, (123)456-7890 or (123)4567890.");
		phoneNumber = sc.nextLine();

		return registrationForm.setUserRegistrationInfo(firstName, lastName, email, 
														emailConfirm, phoneNumber);
	}

}
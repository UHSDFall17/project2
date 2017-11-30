package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes.Return;

class GuestAccount extends UserAccount
{
	public GuestAccount(UserInfo userInfo) 
	{
		this.userInfo = userInfo;
		this.isMember = false;
	}
	
	private static String guestWarning = "\tThis functionality is available only for Members";
	public void createEvent() 	{printGuestWarning(Return.EINSUFFPRIV);}
	public void updateEvent() 	{printGuestWarning(Return.EINSUFFPRIV);}
	public void deleteEvent() 	{printGuestWarning(Return.EINSUFFPRIV);}
	public Return updateProfile() 
	{
		printGuestWarning(Return.EINSUFFPRIV);
		return Return.RESET;
	}

	private void printGuestWarning(Return ret)
	{
		Return.printError(ret);
		System.out.println(guestWarning);
	}	
}
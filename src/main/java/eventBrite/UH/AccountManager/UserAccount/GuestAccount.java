package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes;

class GuestAccount extends UserAccount
{
	public GuestAccount(UserInfo userInfo) 
	{
		this.userInfo = userInfo;
		this.isMember = false;
	}
	
	private static String guestWarning = ": This functionality is available only for Members";
	public void createEvent() {System.out.println(EventTypes.Return.EINSUFFPRIV + guestWarning);}
	public void updateEvent() {System.out.println(EventTypes.Return.EINSUFFPRIV + guestWarning);}
	public void deleteEvent() {System.out.println(EventTypes.Return.EINSUFFPRIV + guestWarning);}
	public void updateProfile() {System.out.println(EventTypes.Return.EINSUFFPRIV + guestWarning);}	
}
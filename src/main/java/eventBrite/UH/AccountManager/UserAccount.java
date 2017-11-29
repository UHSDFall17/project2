package eventBrite.UH.AccountManager;

import eventBrite.UH.EventManager.EventHandler;

public abstract class UserAccount
{
	protected UserInfo 		userInfo;
	protected EventHandler 	eventHandler;
	protected boolean 		isMember;

	protected UserAccount()
	{
		isMember 	 = false;
		eventHandler = EventHandler.getInstance();
	}

	public void 	searchEvent()	{eventHandler.search();}
	public boolean 	isMember() 		{return isMember;}
	public UserInfo getUserInfo() 	{return userInfo;}

	public abstract void createEvent();
	public abstract void updateEvent();	
	public abstract void deleteEvent();
	public abstract void updateProfile();
}

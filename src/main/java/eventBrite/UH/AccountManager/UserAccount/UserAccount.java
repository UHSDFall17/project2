package eventBrite.UH.AccountManager;

import eventBrite.UH.EventManager.EventHandler;

abstract class UserAccount
{
	protected UserInfo 		userInfo;
	protected EventHandler 	eventHandler;

	protected void initialize() 	{eventHandler = EventHandler.getInstance();}
	protected void joinEvent()		{eventHandler.join();}
	protected void searchEvent()	{eventHandler.search();}

	abstract void createEvent();
	abstract void updateEvent();	
	abstract void deleteEvent();
}

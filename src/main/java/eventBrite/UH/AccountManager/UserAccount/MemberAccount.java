package eventBrite.UH.AccountManager;

class MemberAccount extends UserAccount
{
	public MemberAccount(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		if(!isMember)
			isMember = true;
	}

	public void createEvent() {eventHandler.create();}
	public void updateEvent() {eventHandler.update();}
	public void deleteEvent() {eventHandler.delete();}
}
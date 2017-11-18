package eventBrite.UH.AccountManager;

class MemberAccount extends UserAccount
{
	private int memberId;

	public MemberAccount(int id)
	{
		initialize();
		memberId = id;
	}

	public void createEvent() {eventHandler.create();}
	public void updateEvent() {eventHandler.update();}
	public void deleteEvent() {eventHandler.delete();}
}
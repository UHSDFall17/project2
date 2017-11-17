package eventBrite.UH.EventManager;

class EventJoin
{
	private static EventJoin eventJoin = new EventJoin();

	private EventJoin()
	{

	}
	
	public static EventJoin getInstance() {return eventJoin;}
	
}
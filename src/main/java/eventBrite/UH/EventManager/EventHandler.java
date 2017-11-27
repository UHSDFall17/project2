package eventBrite.UH.EventManager;



public class EventHandler
{
	private static EventHandler eventHandler = new EventHandler();
	private EventCreate vEventCreate;
	private EventJoin	vEventJoin;
	private EventSearch vEventSearch;
	// private EventSearch vEventSearch; Not Yet Created

	private EventHandler()
	{
		vEventCreate 	= EventCreate.getInstance();
		vEventJoin		= EventJoin.getInstance();
		vEventSearch	= EventSearch.getInstance();
	}

	public static EventHandler getInstance() {return eventHandler;}
	// Functionalities not implemented yet
	public void create(int id) 	{vEventCreate.createEvent(id);}
	public void update() 	{}	
	public void delete() 	{}
	public void join()		{}
	public void search() 	{vEventSearch.searchEvent();}
}

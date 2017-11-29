package eventBrite.UH.EventManager;

public class EventHandler
{
	private static EventHandler eventHandler = new EventHandler();

	private EventController vEventController;
	private EventJoin		vEventJoin;
	private EventSearch 	vEventSearch;

	private EventHandler()
	{
        vEventController 	= EventController.getInstance();
		vEventJoin			= EventJoin.getInstance();
		vEventSearch		= EventSearch.getInstance();
	}

	public static EventHandler getInstance() {return eventHandler;}
	// Functionalities not implemented yet
	public void create(int id) 	{vEventController.createEvent(id);}
	public void update(int id) 	{vEventController.updateEvent(id);}
	public void delete(int id) 	{vEventController.deleteEvent(id);}
	public void search() 		{vEventSearch.searchEvent();}
	public void join(EventInfo eventInfo) {vEventJoin.joinEvent(eventInfo);}
}


public class ClientMonitor {

	private object comLock;
	private object cordLock;

	private boolean newCommand;
	private boolean newCordinates;
	private boolean isKilled;

	private Arraylist<> cordinates;
	private byte command;


	public ClientMonitor()
	{
		comLock = new Object();
		cordLock = new Object();
		this.newCommand = false;
		this.newCordinates = false;
		this.isKilled = false;
		this.cordinates = null;
		this.command = ' ';
	}

	private void setNewCommand(boolean flag)
	{
		this.newCommand = flag;
	}

	private void setNewCord(boolean flag)
	{
		this.newCordinates = flag;
	}

	public synchronized void setKilled()
	{
		this.isKilled = true;
	}

	public void setCommand(String command)
	{
		synchronized(comLock)
		{
			this.command = command;
			setNewCommand(true);	
		}
	}

	public void setCordinate(ArrayList<> cords)
	{
		synchronized(cordLock)
		{
			this.cordinates = cords;
			setNewCord(true);
		}
	}

	public byte getCommand()
	{
		synchronized(comLock)
		{
			setNewCommand(false);
			return this.command;	
		}
	}

	public ArrayList<> getCordinates()
	{
		synchronized(cordLock)
		{
			setNewCord(false);
			return this.cordinates;	
		}
	}

	public synchronized getIsKilled()
	{
		return this.isKilled;
	}
}

import java.util.Scanner;

public class KBScanner extends thread{
	
	Scanner kb;
	ClientMonitor monitor;
	
	public KBScanner(Scanner kb, ClientMonitor monitor)
	{
		this.kb = kb;
	}
	
	public void run()
	{
		byte command = '';
		line = kb.nextByte()
		while(!line != 'q')
		{
			monitor.setCommand(line); //add new data to the monitor class
			line = kb.nextByte(); //read the next command from the kb
		}
		
		monitor.setKilled(); //tell the other threads that the client should terminate
		return; //end the thread
	}
}

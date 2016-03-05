package Server;

import java.io.IOException;

public class ServerMain {
	
	/*
	 * server startes up it makes a thread pool
	 * the listener pool
	 * 
	 * a manager thread for game logic
	 * 
	 */

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		ServerListener serverListener;
		try 
		{
			serverListener = new ServerListener();
			serverListener.run();
			serverListener.join();
		} 
		catch (Exception e) 
		{
			System.out.println("ServerListener Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}

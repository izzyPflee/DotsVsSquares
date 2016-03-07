package Server;

import java.io.IOException;

import Renderer.GameShape;

public class ServerMain {
	
	/*
	 * server starts up it makes a thread pool
	 * the listener pool
	 * 
	 * a manager thread for game logic
	 * 
	 */

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

		ServerListener _serverListener;
		BroadcastServer _broadcastServer;
		ServerThreadPool _serverThreadPool;

		try 
		{
			//Initialize Variables
			
			
			//start 
			//start the Thread Listener
			
			//
			
			serverListener = new ServerListener();
			serverListener.run();
			serverListener.join();
		} 
		catch (Exception e) 
		{
			System.out.println("ServerListener Failed");
			e.printStackTrace();
		}


	}

}

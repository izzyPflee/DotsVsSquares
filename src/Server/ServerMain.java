package Server;

import java.io.IOException;
import java.util.ArrayList;

import Renderer.GameShape;

public class ServerMain {
	
	/*
	 * server starts up it makes a thread pool
	 * the listener pool
	 * 
	 * a manager thread for game logic
	 * 
	 */
	private static final int THREAD_POOL_SIZE = 10;

	public static void main(String[] args) 
	{
		ServerListener _serverListener;
		BroadcastServer _broadcastServer;
		ServerThreadPool _serverThreadPool;
		String ipAddress = "228.0.0.4";

		try 
		{
			//Initialize Thread Pool and add Threads

			_serverThreadPool = new ServerThreadPool();
			for(int i = 0; i < THREAD_POOL_SIZE; i++){
				//add i for threadId into serverWorkerThread Instance
				_serverThreadPool.addThreadToThreadPool(new ServerWorkerThread(_serverThreadPool, i));

			}

			//start server listener
			_serverListener = new ServerListener(_serverThreadPool);
			_serverListener.start();
			
			_broadcastServer = new BroadcastServer(_serverThreadPool, ipAddress);
			Thread _broadcastThread = new Thread(_broadcastServer);
			 _broadcastThread.start();
			 
			 
			 _broadcastThread.join();
			_serverListener.join();
		} 
		catch (Exception e) 
		{
			System.out.println("ServerListener Failed");
			e.printStackTrace();
		}


	}

}

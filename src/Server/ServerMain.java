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

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

		ServerListener _serverListener;
		BroadcastServer _broadcastServer;
		ServerThreadPool _serverThreadPool;


		try 
		{
			//Initialize Thread Pool and add Threads

			_serverThreadPool = new ServerThreadPool();
			for(int i = 0; i < THREAD_POOL_SIZE; i++){
				//add i for threadId into serverWorkerThread Instance
				_serverThreadPool.addThreadToThreadPool(new ServerWorkerThread());

			}

			//start server listener
			_serverListener = new ServerListener(_serverThreadPool);
			_serverListener.run();
			_serverListener.join();
		} 
		catch (Exception e) 
		{
			System.out.println("ServerListener Failed");
			e.printStackTrace();
		}


	}

}

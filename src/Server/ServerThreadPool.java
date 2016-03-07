package Server;

import java.util.ArrayList;

/*
 * the server thread pool is a thread monitor class
 * 
 * it holds the threads that are receiving move commands from the clients
 *    the threads then update their location in the gameShape array
 * 
 */

public class ServerThreadPool 
{
	private int poolSize;
	ArrayList<ServerWorkerThread> threadPool;
	
	

}

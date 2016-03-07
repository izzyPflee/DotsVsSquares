package Server;

import java.util.ArrayList;

import Renderer.GameShape;

/*
 * the server thread pool is a thread monitor class
 * 
 * it holds the threads that are recieving move commands from the clients
 * 
 */

public class ServerThreadPool {
	private int poolSize;
	ArrayList<ServerWorkerThread> threadPool;
	GameShape[] gameShapes;
	
	public ServerThreadPool(ArrayList<ServerWorkerThread> tPool)
	{
		gameShapes = new GameShape[10];
		threadPool = tPool;
	}
	
	public GameShape[] getGameShapesArray()
	{
		return gameShapes;
	}
}

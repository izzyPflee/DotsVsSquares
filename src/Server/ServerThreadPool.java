package Server;

import java.net.Socket;
import java.util.ArrayList;

import Renderer.GameShape;

/*
 * the server thread pool is a thread monitor class
 * 
 * it holds the threads that are receiving move commands from the clients
 *    the threads then update their location in the gameShape array
 * 
 */

public class ServerThreadPool 
{
	private int _poolSize;
	ArrayList<ServerWorkerThread> _threadPool;
	GameShape[] _gameShapes;
	private boolean _serverKilled;
	private Object _serverKillLock;
	private Object _clientsConnectedLock;
	private int _clientsConnected;
	private ArrayList<Socket> _clientSocketList;
	private int _threadIdNum;

	public ServerThreadPool()
	{
		_poolSize = 10;
		_gameShapes = new GameShape[_poolSize];
		_threadPool = new ArrayList<>();
		_serverKilled = false;
		_serverKillLock = new Object();
		_clientsConnectedLock = new Object();
		_clientsConnected = 0;
		_clientSocketList = new ArrayList<Socket>();
		_threadIdNum = 0;
	}

	public GameShape[] getGameShapesArray()
	{
		return _gameShapes;
	}

	public boolean isServerKill()
	{
		synchronized(_serverKillLock)
		{
			return _serverKilled;			
		}
	}

	public void setKillServer()
	{
		synchronized(_serverKillLock)
		{
			this._serverKilled = true;
		}
	}

	public int getClientsConneted()
	{
		synchronized(_clientsConnectedLock)
		{
			return this._clientsConnected;
		}
	}

	public synchronized boolean addNewClient(Socket newClient)
	{
		this._clientSocketList.add(newClient);
		notifyAll();
		return true;
	}

	public synchronized Socket getNewClient()
	{
		while(_clientSocketList.size() == 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return _clientSocketList.remove(0);
	}

	public void addThreadToThreadPool(ServerWorkerThread workerThread){

		//check for outofbounds error
		if(_threadIdNum >= _poolSize){
			System.out.println("Error: Thread pool limited to 9 threads!");
			return;
		}

		//check for null worker thread
		if(workerThread == null){
			System.out.println("thread is null!");
			return;
		}

		//add thread to thread pull
		_threadPool.add(workerThread);
		_threadIdNum++;

	}

	public void startWorkerThreads() {

		for(ServerWorkerThread thread: _threadPool)
			thread.start();
	}
}
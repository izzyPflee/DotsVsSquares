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
	private ArrayList<Socket> _newClients;

	public ServerThreadPool(ArrayList<ServerWorkerThread> tPool)
	{
		this._gameShapes = new GameShape[10];
		this._threadPool = tPool;
		this._serverKilled = false;
		this._serverKillLock = new Object();
		this._clientsConnectedLock = new Object();
		this._clientsConnected = 0;
		this._newClients = new ArrayList<Socket>();
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
		this._newClients.add(newClient);
		return true;
	}

	public synchronized Socket getNewClient()
	{
		if(this._newClients.size() > 0)
		{
			return this._newClients.remove(0);
		}
		return null;
	}

}
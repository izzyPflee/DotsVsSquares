package Server;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/*
 * server listener listens on a serverSocket for new clients and then passes
 * the new sockets to the thread pool or tells the client that the number of players
 * is mazed out and the clinet needs to try again later
 */

public class ServerListener extends Thread{

	private ServerSocket _listener;
	private Socket _socket;
	private int _portNumber = 9898;
	private ServerThreadPool _serverThreadPool;

	public ServerListener(ServerThreadPool serverThreadPool) throws IOException
	{
		this._serverThreadPool = serverThreadPool;
	}

	public void run()
	{
		try
		{
			this._listener = new ServerSocket(_portNumber);
			System.out.println("__________Server Listener online__________");

			while(_serverThreadPool.isServerKill() == false)
			{
				_socket = _listener.accept();

				BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
				PrintWriter out = new PrintWriter(_socket.getOutputStream(), true);

				if(_serverThreadPool.getClientsConneted() >=  10)
				{
					out.write(0); //no room you cannot connect to the game
					out.flush();
					continue; //start the loop again;
				}

					out.write(1); //there is room you are getting connect to the server now
					out.flush();
				

				int line = 0;

				while((char)line != 'Q')
				{ 
					line = in.read();

					System.out.println((char)line);
				}
			}

		}
		catch(Exception e)
		{

		}
	}
}

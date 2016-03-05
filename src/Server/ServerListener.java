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
 * server listener listenes on a serverSOcket for new clients and then passes
 * the new sockets to the thread pool or tells the client that the number of players
 * is mazed out and the clinet needs to try again later
 */

public class ServerListener extends Thread{
	
	ServerSocket listener;
	Socket socket;
	int portNumber = 9898;
	
	public ServerListener() throws IOException
	{
	}
	
	public void run()
	{
		try
		{
		this.listener = new ServerSocket(9898);
		System.out.println("Server online");
		
		Socket socket;
			socket = listener.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			int line = 0;
			
			while((char)line != 'Q')
			{
				 
				line = in.read();
				
						
				System.out.println((char)line);

			}
		}
		catch(Exception e)
		{
			
		}
	}

}

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
		this.listener = new ServerSocket(portNumber);

		System.out.println("Server started and running");
	}
	
	public void run()
	{
		try
		{
			//listener.setSoTimeout(1000);
		}
		catch(Exception e)
		{
			
		}
		try
		{
			while(true)
			{

				socket = this.listener.accept();
				PrintWriter out =  new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				//in.readLine();
				//out.write("Welcome");
				//out.flush();
				System.out.print("Server connected to client");
				
				
				String input = in.readLine();
				while(!input.equals(KeyEvent.VK_Q + ""));
				{
					System.out.println("IN the Server: " + input);
					input = in.readLine();
				}
				
			}
		}
		catch(Exception e)
		{
			
		}
	}

}

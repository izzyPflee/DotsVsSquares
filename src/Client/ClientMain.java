<<<<<<< HEAD
package Client;

/*
 * clientMain connects to the server, creates the other threads and sends move requests to the server
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Renderer.GameShape;
import Renderer.Renderer;

public class ClientMain extends JFrame implements KeyListener {

	private String serverAddress = "localhost"; //This needs to be changed to the servers hard coded address
	private int portNumber = 9898;
	private Socket socket;
	private ArrayList<GameShape> gameShapeArray;
	private ClientKeyEventHandler commandForServer;
	private Renderer renderer;


	public static void main(String[] args)
	{
		new ClientMain();
	}

	public ClientMain()
	{


		try 
		{
			
			socket = new Socket(serverAddress, portNumber);
			PrintWriter out =  new PrintWriter(socket.getOutputStream(), true);
			out.println("join");
			//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Here in client");
			//String startGame = in.readLine();
			//System.out.println(startGame);

			//if(startGame.compareTo("NO ROOM") == 0) //if Server is full or unable to accept players it 
			//{									//will respond with "NO ROOM"
			//	System.out.println("No Room On Server Ending game");
			//	return;
			//}
	

			//======================================After connection with server is successful============
			
	
			gameShapeArray = new ArrayList<GameShape>();
			

			renderer = new Renderer(800, 800, this);
			this.addKeyListener(this);
			commandForServer = new ClientKeyEventHandler(out);
			

		} 
		catch (Exception e) 
		{
			System.out.println("There Was An ERROR and the Game CANNOT run now.");
			e.printStackTrace();
		}


	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//send the command to the server
		commandForServer.processKey(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) { //unused
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) { //unused
		// TODO Auto-generated method stub

	}



}
=======
package Client;

/*
 * clientMain connects to the server, creates the other threads and sends move requests to the server
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Renderer.GameShape;
import Renderer.Renderer;

public class ClientMain extends JFrame implements KeyListener {

	private String serverAddress = "localhost"; //This needs to be changed to the servers hard coded address
	private int portNumber = 9898;
	private Socket socket;
	private ArrayList<GameShape> gameShapeArray;
	private ClientKeyEventHandler commandForServer;
	private Renderer renderer;


	public static void main(String[] args)
	{
		new ClientMain();
	}

	public ClientMain()
	{


		try 
		{
			
			socket = new Socket(serverAddress, portNumber);
			PrintWriter out =  new PrintWriter(socket.getOutputStream(), true);
			out.println("join");
			//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Here in client");
			//String startGame = in.readLine();
			//System.out.println(startGame);

			//if(startGame.compareTo("NO ROOM") == 0) //if Server is full or unable to accept players it 
			//{									//will respond with "NO ROOM"
			//	System.out.println("No Room On Server Ending game");
			//	return;
			//}
	

			//======================================After connection with server is successful============
			
	
			gameShapeArray = new ArrayList<GameShape>();
			

			renderer = new Renderer(800, 800, this);
			this.addKeyListener(this);
			commandForServer = new ClientKeyEventHandler(out);
			

		} 
		catch (Exception e) 
		{
			System.out.println("There Was An ERROR and the Game CANNOT run now.");
			e.printStackTrace();
		}


	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//send the command to the server
		commandForServer.processKey(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) { //unused
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) { //unused
		// TODO Auto-generated method stub

	}



}
>>>>>>> perrydev

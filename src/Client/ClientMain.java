package Client;

import java.net.Socket;


/*
 * clientMain connects to the server, creates the other threads and sends move requests to the server
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.util.ArrayList;

import javax.swing.JFrame;

import Renderer.GameBoard;
//import Renderer.GameShape;
import Renderer.Renderer;

public class ClientMain extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _serverAddress = "localhost"; //This needs to be changed to the servers hard coded address
	private int _portNumber = 9898;
	private Socket _socket;
	//private ArrayList<GameShape> _gameShapeArray;
	private ClientKeyEventHandler _clientKeyEventHandler;
	private Renderer _renderer;
	private ClientGameStateReceiver _gameStateReceiver;
	private boolean _NotQuitting = true;


	public static void main(String[] args)
	{
		new ClientMain();
	}

	public ClientMain()
	{
		try 
		{
			//Let server know that client wants to play
			//Create new _socket for communicating with server using TCP/IP

			_socket = new Socket(_serverAddress, _portNumber);

			//Create stream to write
			PrintWriter out =  new PrintWriter(_socket.getOutputStream(), true);
			//get stream for input
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));

			//wait for server response
			int serverResp = in.read();
			
			//if Server is full or unable to accept players it
			//will respond with a 0
			if(serverResp == 0){
				System.out.println("No Room On Server Ending game");
				return;
			}
			else
			{
				//======================================After connection with server is successful============
				System.out.println("Connection Established with server!");
			}
			
			//_gameShapeArray = new ArrayList<GameShape>();

			_renderer = new Renderer(GameBoard.WORLD_BOUNDS, GameBoard.WORLD_BOUNDS, this);
			this.addKeyListener(this);
			_clientKeyEventHandler = new ClientKeyEventHandler(out);

			//start thread that handles all output from server and GUI updating

			//TODO: add a scanner to have the user input a ip-address to connect
			//for now hard code a class D (multi-cast) ip address to receive server broadcasts
			String ipAddress = "228.0.0.4";
			_gameStateReceiver = new ClientGameStateReceiver(_renderer, ipAddress);
			Thread gameReceiver = new Thread(_gameStateReceiver);

			gameReceiver.start();
			
			gameReceiver.join();

		} 
		catch (Exception e) 
		{
			System.out.println("There Was An ERROR and the Game CANNOT run now.");
			e.printStackTrace();
		}

		System.out.println("End of Client Thread");


	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//send the command to the server
		this._NotQuitting = _clientKeyEventHandler.processKey(arg0);
		if(!_NotQuitting)
		{
			try 
			{
				this._socket.close(); //removes the client from the game and makes the terminal an oberver terminal
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
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


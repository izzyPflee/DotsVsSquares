package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Renderer.GameShape;

public class ClientMain {


	public static void main(String[] args)
	{
		String serverAddress = "localhost"; //This needs to be changed to the servers hard coded address
		int portNumber = 9898;
		Socket socket;
		ArrayList<GameShape> gameShapeArray;

		try 
		{
			socket = new Socket(serverAddress, portNumber);

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String startGame = in.readLine();

			if(startGame.compareTo("NO ROOM") == 0) //if Server is full or unable to accept players it 
			{									//will respond with "NO ROOM"
				System.out.println("No Room On Server Ending game");
				return;
			}
			
			
			//======================================After connection with server is successful============

			gameShapeArray = new ArrayList<GameShape>();
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			
			
			

		} 
		catch (IOException e) 
		{
			System.out.println("There Was An ERROR and the Game CANNOT run now.");
			e.printStackTrace();
		}




		





	}



}

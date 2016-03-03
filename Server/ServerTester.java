package Server;

import Client.ClientGameStateReceiver;
import Renderer.GameShape;

//If getting Can't assign requested address, pass this argument
//-Djava.net.preferIPv4Stack=true


public class ServerTester {

	public static void main(String[] args) 
	{

		int i = 0;
		final int MAX_PLAYERS = 5;
		GameShape[] playerShapes = new GameShape[MAX_PLAYERS];
		
		try{
			Thread serverThread = new Thread(new  BroadcastServer (playerShapes));
			serverThread.start();
			
			 byte[] playersCoordinates = new byte[MAX_PLAYERS * 4];
	
			 System.out.println("Size:" + playersCoordinates.length);
			 
			 
			Thread clientThread1 = new Thread(new ClientGameStateReceiver(++i));
			Thread clientThread2 = new Thread(new ClientGameStateReceiver(++i));
			Thread clientThread3 = new Thread(new ClientGameStateReceiver(++i));
	
		clientThread1.start();
				
			Thread.sleep(10000);
			clientThread2.start();
			//Thread.sleep(10000);
			//clientThread3.start();
		
		}catch(Exception e)
		{
			System.out.println("ERRROR!!!!!");
		}

	}

}

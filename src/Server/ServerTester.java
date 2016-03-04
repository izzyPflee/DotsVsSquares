package Server;

import Client.ClientGameStateReceiver;
import Renderer.GameShape;

//If getting Can't assign requested address, pass this argument
//-Djava.net.preferIPv4Stack=true


import Client.ClientGameStateReceiver;

public class ServerTester {

	public static void main(String[] args) 
	{

		int i = 0;
		final int MAX_PLAYERS = 1;
		GameShape[] playerShapes = new GameShape[MAX_PLAYERS];
		
		
		
		int test = 465;
		byte top = (byte) (test >> 8);
		byte bottom = (byte)test;
		
		
		int topInt = top  & (0xff);
		int botInt = bottom  & (0xff);
		int x = (topInt << 8) | botInt;
		
		
		
		System.out.println(x);
		
		
		
		

		try{
			Thread serverThread = new Thread(new  BroadcastServer (playerShapes));
			serverThread.start();


			Thread clientThread1 = new Thread(new ClientGameStateReceiver(++i, MAX_PLAYERS));
		//	Thread clientThread2 = new Thread(new ClientGameStateReceiver(++i, MAX_PLAYERS));
		//	Thread clientThread3 = new Thread(new ClientGameStateReceiver(++i, MAX_PLAYERS));

			clientThread1.start();

			clientThread1.join();
			serverThread.join();
			//	clientThread2.start();
			//Thread.sleep(10000);
			//clientThread3.start();

		}catch(Exception e)
		{
			System.out.println("ERRROR!!!!!");
		}

	}

}

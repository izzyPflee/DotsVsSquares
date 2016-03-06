package Server;

import Client.ClientGameStateReceiver;
import Renderer.GameShape;
import Renderer.GameBoard;

//If getting Can't assign requested address, pass this argument
//-Djava.net.preferIPv4Stack=true


import Client.ClientGameStateReceiver;
import Renderer.Renderer;
import Renderer.ShapeType;

import java.util.ArrayList;

public class ServerTester {

	public static void main(String[] args) 
	{
		//for(int x = 0; x < 100; x ++)
			//stressTest();

		int id = 0;
		final int MAX_PLAYERS = 10;

		

		try{
			Thread serverThread = new Thread(new  BroadcastServer ( MAX_PLAYERS));
			serverThread.start();
		}
		catch(Exception e)
		{
			System.out.println("ERRROR!!!!!");
		}
		
	}


	public static void stressTest()
	{
		ArrayList<GameShape> list;
	
			
			long start = System.nanoTime();

			 list = new ArrayList<>();

			for(int i = 0; i < 10; i++)
			{
				list.add(new GameShape(10, ShapeType.CIRCLE,50,300, 20, 40));
			}


			long end = System.nanoTime();

			long result = end - start;

			System.out.println("TIME is: " + result/1000);

			start = System.nanoTime();


			for(GameShape shape: list)
			{
				shape.get_x();

			}

			end = System.nanoTime();
			long result2 = end - start;
			System.out.println("TIME is: " + result2 /1000);
			System.out.println("*******************************************");
		}

}

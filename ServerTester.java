//If getting Can't assign requested address, pass this argument
//-Djava.net.preferIPv4Stack=true


public class ServerTester {

	public static void main(String[] args) 
	{
		
		int i = 0;
		final int MAX_PLAYERS = 5;
		
		try{
			Thread serverThread = new Thread(new  BroadcastServer (MAX_PLAYERS));
			serverThread.start();
			
			 byte[] playersCoordinates = new byte[MAX_PLAYERS * 4];
	
			 System.out.println("Size:" + playersCoordinates.length);
			 
			 
			Thread clientThread1 = new Thread(new ClientGameStateReceiver(++i , playersCoordinates, MAX_PLAYERS));
			Thread clientThread2 = new Thread(new ClientGameStateReceiver(++i , playersCoordinates, MAX_PLAYERS));
			Thread clientThread3 = new Thread(new ClientGameStateReceiver(++i , playersCoordinates, MAX_PLAYERS));
	
		clientThread1.start();
				
			//Thread.sleep(10000);
			clientThread2.start();
			//Thread.sleep(10000);
			clientThread3.start();
		
		}catch(Exception e)
		{
			System.out.println("ERRROR!!!!!");
		}

	}

}

import java.io.*;
import java.net.*;
import java.util.*;
 
public class ClientGameStateReceiver implements Runnable
{    
	private int _id;
	private byte[] _playerCoordinates;
	private final int MAX_PLAYERS;
	
	public ClientGameStateReceiver(int id, byte[] playerCoordinates, int maxPlayers)
	{	
		_id =id;
		_playerCoordinates = playerCoordinates;
		MAX_PLAYERS = maxPlayers;
	}
	
	private void printPlayers()
    {
		 int		count = 0;

		 for(int i =0; i < MAX_PLAYERS * 2; i += 2)
		 {
			 count ++;
			 int x =  _playerCoordinates[i];
			 int y =  _playerCoordinates[i + 1];
			 System.out.println("Client " + _id + " ---> Player #" + count + " x=" + x + " y=" + y);
		 }
    }
	 
	public void run(){
		
		   MulticastSocket socket;
		   
		   
		try {
			
			socket = new MulticastSocket(8888);
	
	        InetAddress address = InetAddress.getByName("228.0.0.4");
	        socket.joinGroup(address);
	 
	        DatagramPacket packet;
	        
	        boolean go = true;
	        
	        while(go)
	        {
	        		packet = new DatagramPacket(_playerCoordinates, _playerCoordinates.length);
	      
	        		socket.receive(packet);
	        		_playerCoordinates = packet.getData();
	    
	        		printPlayers();
	        			        		
	  
	        }

	    socket.leaveGroup(address);
	    socket.close();
	    
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
}

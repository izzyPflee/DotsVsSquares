package Client;

import java.awt.Point;
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

		 for(int i =0; i < MAX_PLAYERS * 4; i += 4)
		 {
			 count ++;
			 byte x_top =  _playerCoordinates[i];
			 byte x_bottom =  _playerCoordinates[i + 1];
			 byte y_top =  _playerCoordinates[i + 2];
			 byte y_bottom =  _playerCoordinates[i + 3];
			 int x = ((int)x_top << 8) + x_bottom;
			 int y = ((int)y_top << 8) + y_bottom;
			 System.out.println("Client " + _id + " ---> Player #" + count + " x=" + x + " y=" + y);
		 }
    }
	 
	public void run(){
		
		   MulticastSocket socket;
		   
		   
		try {
			
			socket = new MulticastSocket(8888);
	        InetAddress address = InetAddress.getByName("228.0.0.4");
	        socket.joinGroup(address);
	        byte[] recvBuf = new byte[5000];
	        DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);;
	        
	        socket.receive(packet);
                                                 
	        int byteCount = packet.getLength();
	        
	        System.out.println("BYTE COUNT: " + byteCount);
	        ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
	        
	        ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
	        
	        try{
	        	
	        	Point[] test = (Point[]) is.readObject();
	        	
	        	
	        	for(int i = 0; i< test.length; i++)
	        	{	
	        	  System.out.println("Client #" + _id +" received: " + test[i]);
	        	}
	        	
	        }catch(StreamCorruptedException e)
	        {
	        	System.out.println("ERROR IN HERE");
	        	e.printStackTrace();
	        	
	        }
	        finally{	       
	        	is.close();
	      
	        }
	        
	        
	  
	        
	        
	        
	       /* 
	        
	        
	        boolean go = true;
	        // ObjectInputStream objIn = new ObjectInputStream();
	        while(go)
	        {
	        		packet = new DatagramPacket(_playerCoordinates, _playerCoordinates.length);
	      
	        		socket.receive(packet);
	        		
	        		
	        		
	        		
	        	//	_playerCoordinates = packet.getData();
	        		
	        		//ObjectInputStream inputStream = new ObjectInputStream();
	    
	        		printPlayers();
	        			        		
	  
	        }
	*/
	    socket.leaveGroup(address);
	    socket.close();
	    
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
}

import java.io.*;
import java.net.*;
import java.util.*;

public class BroadcastServer implements Runnable{

    private DatagramSocket socket = null;
    private boolean isServing = true;
    private long waitTime = 50;
    private final int MAX_PLAYER;
    private byte[] players_coordinates;
    
    
    public BroadcastServer(int maxPlayers) throws IOException 
    {
    		System.out.println("Size of Int " + Integer.SIZE);
    		MAX_PLAYER = maxPlayers;
    		players_coordinates = new byte[Integer.SIZE * MAX_PLAYER * 2];
	    	
    		try{
	        socket = new DatagramSocket(8887);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    }
    
    private void generateRandomTest()
    {
    		Random rand = new Random();
    		for(int i = 0; i < MAX_PLAYER*2; i ++)
        	{
        		players_coordinates[i] = (byte) rand.nextInt(100);
        	}
    }
   
    private void printPlayers()
    {
     	System.out.print("Server says: ");
     	for(int i = 0; i < MAX_PLAYER*2; i ++)
     		System.out.print(players_coordinates[i] + ", ");
     	System.out.println("");
    }
    
    @Override
    public void run() {
    		try { 
    			
    			InetAddress group = InetAddress.getByName("228.0.0.4");
    			DatagramPacket packet;
			while (isServing)
             {
        	  		generateRandomTest();
        	  		printPlayers();
        	  		packet = new DatagramPacket(players_coordinates, players_coordinates.length, group, 8888);
        	  		socket.send(packet);
              }
     
          } catch (IOException e) {
                e.printStackTrace();
                isServing = false;
            }
        	socket.close();
    }
}

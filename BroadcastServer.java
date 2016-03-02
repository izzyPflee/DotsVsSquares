import java.awt.Point;
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
    		players_coordinates = new byte[ MAX_PLAYER * 4];
	    	
    		try{
    			socket = new DatagramSocket(8887);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
    }
    
    private void generateRandomTest()
    {
    	int testInt = 1024;
		Random rand = new Random();
		for(int i = 0; i < MAX_PLAYER*4; i += 4)
    	{
    		players_coordinates[i] =(byte) (testInt >> 8);// (byte) rand.nextInt(100);
    		players_coordinates[i + 1] =(byte) (testInt <<8);
    		players_coordinates[i +2] =(byte) (testInt >> 8);// (byte) rand.nextInt(100);
    		players_coordinates[i + 3] =(byte) (testInt <<8);
    	}
    }
   
    private void printPlayers()
    {
     	System.out.print("Server says: ");
     	for(int i = 0; i < MAX_PLAYER*4; i ++)
     		System.out.print(players_coordinates[i] + ", ");
     	System.out.println("");
    }
    
    @Override
    public void run() {
    		
    			  
    			   
    			try {
    				 InetAddress groupAddress = InetAddress.getByName("228.0.0.4");
    				
    				 Scanner kb = new Scanner(System.in);
    				
    				System.out.println("Server enter a key...");
    				kb.nextLine();
    				
    				
    				Point pt = new Point(2,3);
    				Point [] points = {new Point(2,3), new Point(12,13), new Point(22,23), new Point(32,33), new Point(42,43)};
    			
    				ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
    				
    				ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
    				
    				os.flush();
    				os.writeObject(points);
    				os.flush();
    				
    				byte[] sendBuf = byteStream.toByteArray();
    				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, groupAddress, 8888);
    			
    				 int byteCount = packet.getLength();
    			
    				  socket.send(packet);
    				  os.close();
    				
    				
    			/*	
    				
    				
			while (isServing)
             {
        	  		
				
				
				
				
				
				
				
				
				
				
        	  		try{
        	  		Thread.sleep(1000);
        	  		}catch(Exception e){}
              }*/
     
          } catch (IOException e) {
                e.printStackTrace();
                isServing = false;
            }
        	socket.close();
    }
}

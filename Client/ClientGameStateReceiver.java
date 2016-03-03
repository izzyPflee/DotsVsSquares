package Client;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;

import Renderer.GameShape;

public class ClientGameStateReceiver implements Runnable
{    
	private int _id;

	public ClientGameStateReceiver(int id)
	{	
		_id =id;
	}

	private void printPlayers(GameShape playerShapes)
	{
		System.out.println(playerShapes.toString());
		/*
		for(int i = 0; i< playerShapes.length; i++)
		{	
			System.out.println("Client #" + _id +" received: " + playerShapes[i].toString());
		}*/
	}
	
	/*
	 * Reading Objects from an ObjectInputStream is more complicated than we originally thought.
	 * As of right now, I had to recreate the connections every iteration to allow clients to 
	 * read new data. The BroadcastServer had to use writeUnshared instead of writeObject()
	 * because it optimizes network traffic by only sending a pointer to an already sent object, 
	 * unless specified as "unshared".
	 * They both know an object has already been sent because the two Streams cache the objects
	 * in their buffers! This is also why I had to reset the ObjectOutputStream in the server, 
	 * it now flushes the cache of which objects have been sent before.
	 * 
	 * When the server was on a 1 second sleep, the client read too fast and would re-read the
	 * same object from its buffer twice. So for now I put both client/server on the same time 
	 * quatum of sleep...
	 * 
	 * We really need to figure out a way to not have to re-instantiate the streams every iteration.
	 * I read this shouldn't be necessary and is not efficient, but I cannot figure out how avoid it
	 * if we want the server to not send just the first object everytime...
	 * 
	 */
	
	public void run(){
		try {
			MulticastSocket socket = null;
			ByteArrayInputStream byteStream;
			boolean connected = true;
			DatagramPacket packet;
			ObjectInputStream is = null;
			InetAddress address = InetAddress.getByName("228.0.0.4");
			
			byte[] recvBuf = new byte[5000];
		
			while(connected)
			{
				socket = new MulticastSocket(8888);
				socket.joinGroup(address);
				packet = new DatagramPacket(recvBuf,recvBuf.length);
				socket.receive(packet);
				//int byteCount = packet.getLength();
				//System.out.println("BYTE COUNT: " + byteCount);

				byteStream = new ByteArrayInputStream(recvBuf);

				is = new ObjectInputStream(new BufferedInputStream(byteStream));
				
				try{
					
					GameShape playerShapes = (GameShape) is.readObject();
					
					printPlayers(playerShapes);
					
					//Need to hand the playerShapes off to the Renderer
					
				}
				catch(StreamCorruptedException e)
				{
					System.out.println("ERROR while reading object in client");
					e.printStackTrace();
				}
				is.close();
				is =  null;
				socket = null;
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				
				
				Thread.sleep(1000);
			}//end while

			is.close();


			socket.leaveGroup(address);
			socket.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

package Server;
import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;

import Renderer.GameShape;
import Renderer.ShapeType;

public class BroadcastServer implements Runnable{

	private DatagramSocket socket = null;
	private boolean isServing = true;
	private final int MAX_PLAYERS ;

	int x = 0;
	int y = 0;

	public BroadcastServer( int maxPlayers ) throws IOException 
	{
		System.out.println("Size of Int " + Integer.SIZE);
		MAX_PLAYERS = maxPlayers;

		try{
			socket = new DatagramSocket(8887);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static byte convertIntToByteUpper(int num)
	{
		return (byte) (num >> 8);
	}
	
	private static byte convertIntToByteLower(int num)
	{
		return (byte) num;
	}


	/*
	 * This is just a test so far. It sents the 5 bytes representing a player's id and the top
	 * and bottom 8-bits of its x and y integer coordinates.
	 * The client just converts the bytes back to integers with bitwise operators to avoid
	 * the signed nature of everything in this forsaken language.
	 * 
	 * The x and y are just incrementing by one on each iteration as a proof of concept. 
	 * */
	
	@Override
	public void run() {
		try {

			InetAddress groupAddress = InetAddress.getByName("228.0.0.4");
	
			while (isServing)
			{

				byte[] sendBuf = new byte[5];//setRandomPlayerShapes(); //byteStream.toByteArray();
				
				sendBuf[0] = 7; //player id
				sendBuf[1] = convertIntToByteUpper(x); //player top half x
				sendBuf[2] = convertIntToByteLower(x); //player bottom half x
				sendBuf[3] = convertIntToByteUpper(y); //top half x
				sendBuf[4] = convertIntToByteLower(y); //bottom half y

				System.out.println("Server: x = " + x + " y = " +y);
				x++; y++;
				
				
				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, groupAddress, 8888);

				int byteCount = packet.getLength();
				
				//System.out.println("This is the size of the packet:" + byteCount);
				socket.send(packet);


				try{
					Thread.sleep(10);
				}catch(Exception e){}

				
				if(x > 1024)
					isServing = false;
				
			}//end while

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			isServing = false;
		}
	}
}

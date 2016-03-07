package Server;

import java.io.*;
import java.net.*;

import Renderer.GameShape;

public class BroadcastServer implements Runnable{

	private DatagramSocket socket = null;
	private boolean isServing = true;

	public BroadcastServer( ServerThreadPool threadPool ) throws IOException 
	{
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

	private byte[] buildByteArray()
	{
		byte[] sendBuf = new byte[50];
		return sendBuf;
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

				byte[] sendBuf = new byte[50];
				
				sendBuf[0] = 7; //player id
				sendBuf[1] = convertIntToByteUpper(0); //player top half x
				sendBuf[2] = convertIntToByteLower(0); //player bottom half x
				sendBuf[3] = convertIntToByteUpper(0); //top half x
				sendBuf[4] = convertIntToByteLower(0); //bottom half y			
				
				
				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, groupAddress, 8888);

				int byteCount = packet.getLength();
		
				socket.send(packet);

				try{
					Thread.sleep(10);
				}catch(Exception e){}
				
			}//end while

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			isServing = false;
		}
	}
}

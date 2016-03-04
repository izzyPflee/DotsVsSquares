package Client;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;

import Renderer.GameShape;

public class ClientGameStateReceiver implements Runnable
{    
	private int _id;
	private final int MAX_PLAYERS;
	private  ArrayList<GameShape> _playerShapes;
	
	
	
	public ClientGameStateReceiver(int id, int max)
	{	
		_id =id;
		MAX_PLAYERS = max;
		_playerShapes = new ArrayList<>();
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
	
	private ArrayList<GameShape> updatePlayerShapes(byte[] players)
	{
		
		
	
	
		return _playerShapes;
	}
	
	
	//Max ID is 7, the maximum players is 8 in the game (0 - 7)
	private int parseID(byte idByte)
	{
		return idByte >>> 4;
	}
	
	/*private int parseX(byte topByte, byte botByte)
	{
		int x;
		
		byte xTop = (byte) (topByte << 4);
		
		int xTopInt = xTop << 4;
		
		byte xBot = botByte;
		
		x = (int) (xTopInt | xBot);
		
		return x;
	}*/
	
	private int parseCoordinate(byte topByte, byte botByte)
	{
		int topInt = topByte  & (0xff);
		int botInt = botByte  & (0xff);
		int integer = (topInt << 8) | botInt;
		return integer;
	}
	
	public void run(){
		try {
			MulticastSocket socket = null;
			//ByteArrayInputStream byteStream;
			boolean connected = true;
			DatagramPacket packet;
			//ObjectInputStream is = null;
			InetAddress address = InetAddress.getByName("228.0.0.4");
			
			byte[] recvBuf = new byte[5];
			socket = new MulticastSocket(8888);
			socket.joinGroup(address);
			packet = new DatagramPacket(recvBuf,recvBuf.length);
			
			while(connected)
			{
				socket.receive(packet);
				
				int id = recvBuf[0];//
				int x = parseCoordinate(recvBuf[1], recvBuf[2]);
				int y = parseCoordinate(recvBuf[3], recvBuf[4]);
				
				//char y = parseCoordinate(recvBuf[3], recvBuf[4]);
				
				//int xInt = (int) x;
				//int yInt = (int) y;
			
				
			//	System.out.println("CLIENT id == " + id );
				
				System.out.println("Client's x = "+ x + " y = " + y);
			
				
				Thread.sleep(10);
			}//end while

			socket.leaveGroup(address);
			socket.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

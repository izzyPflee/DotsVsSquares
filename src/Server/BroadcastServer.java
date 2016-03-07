package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Renderer.GameShape;

public class BroadcastServer implements Runnable
{
	private DatagramSocket _socket = null;
	private boolean _isServing = true;
	private ServerThreadPool _threadPool;
	private static final int _MAX_PLAYERS_BYTES = 50;
	
	private final int _port = 8888;
	private final String _ipAddress;
	
	public BroadcastServer( ServerThreadPool threadPool, String ipAddress ) throws IOException 
	{
		if(threadPool != null && ipAddress != null) 
		{	
			_threadPool = threadPool;
			_ipAddress = ipAddress;
		}
		else
		{
			throw new NullPointerException();
		}
		
		try{
			_socket = new DatagramSocket(_port);
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
	 * Iterates through the monitor's list of GameShapes, 
	 * for each active player GameShape, pack into the byte array
	 * for broadcast to all active clients' renders
	 */
	private byte[] buildByteArray()
	{
		byte[] sendBuf = new byte[ _MAX_PLAYERS_BYTES ];
		GameShape[] shapes = _threadPool.getGameShapesArray();
		int x = 0, y = 0;
		int i = 0;
		
		for(GameShape shape : shapes)
		{
			if(shape != null)
			{
				x = shape.get_x();
				y = shape.get_y();
			
				sendBuf[i++] = (byte) shape.get_shapeID(); //player color id
				sendBuf[i++] = convertIntToByteUpper(x); //player top half x
				sendBuf[i++] = convertIntToByteLower(x); //player bottom half x
				sendBuf[i++] = convertIntToByteUpper(y); //top half x
				sendBuf[i++] = convertIntToByteLower(y); //bottom half y	
			}
		}

		return sendBuf;
	}

	@Override
	public void run() {
		try {
			byte[] sendBuf;
			InetAddress groupAddress = InetAddress.getByName(_ipAddress);
	
			while (_isServing)
			{
				sendBuf = buildByteArray();
				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, groupAddress,8887);
				_socket.send(packet);

				try{
					Thread.sleep(10);
				}catch(Exception e){}
				
			}//end while

			_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			_isServing = false;
		}
	}
}

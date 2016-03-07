package Client;


import java.net.*;
import java.util.*;

import Renderer.GameShape;
import Renderer.Renderer;
import Renderer.ShapeType;

public class ClientGameStateReceiver implements Runnable
{    
	private int _id;
	private Renderer _renderer;

	public ClientGameStateReceiver(Renderer renderer)
	{	
		_renderer = renderer;
	}

	public void run(){
		try {
			MulticastSocket socket = null;
			//ByteArrayInputStream byteStream;
			boolean connected = true;
			DatagramPacket packet;
			//ObjectInputStream is = null;
			InetAddress address = InetAddress.getByName("228.0.0.4");
			
			byte[] recvBuf = new byte[50];
			socket = new MulticastSocket(8888);
			socket.joinGroup(address);
			packet = new DatagramPacket(recvBuf,recvBuf.length);
			
			while(connected)
			{
				socket.receive(packet);
				
				ClientGameStateParser.parse(recvBuf);
				
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

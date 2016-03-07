package Client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import Renderer.GameShape;
import Renderer.Renderer;

public class ClientGameStateReceiver implements Runnable
{    
	private final Renderer _renderer;
	private final String _ipAddress;
	private static final int _MAX_PLAYERS_BYTES = 50;
	private final int _port = 8887;
	private boolean connected = true;
	
	public ClientGameStateReceiver(Renderer renderer, String ipAddress)
	{	
		if(renderer != null && ipAddress != null){
			_renderer = renderer;
			_ipAddress = ipAddress;
		}else{
			throw new NullPointerException();
		}
	}

	public void run(){
		try {
			MulticastSocket socket = null;
			DatagramPacket packet;
			InetAddress address = InetAddress.getByName(_ipAddress);
			ArrayList<GameShape> shapes;
			byte[] recvBuf = new byte[_MAX_PLAYERS_BYTES];
			socket = new MulticastSocket(_port);
			socket.joinGroup(address);
			packet = new DatagramPacket(recvBuf,recvBuf.length);
			
			while(connected)
			{
				socket.receive(packet);
				shapes = ClientGameStateParser.parse(recvBuf);
				_renderer.update(shapes);
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

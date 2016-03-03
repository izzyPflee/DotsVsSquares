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
	private long waitTime = 50;
	private final int MAX_PLAYERS;
	private GameShape[] _playerShapes;


	public BroadcastServer(GameShape[] playerShapes) throws IOException 
	{
		System.out.println("Size of Int " + Integer.SIZE);
		MAX_PLAYERS = playerShapes.length;
		_playerShapes = playerShapes;

		try{
			socket = new DatagramSocket(8887);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void setRandomPlayerShapes()
	{
		Random rand = new Random();
		_playerShapes = new GameShape[MAX_PLAYERS];
		int i = 0;
		for(GameShape player: _playerShapes)
		{
			int x = rand.nextInt(100);
			int y = rand.nextInt(100);
			_playerShapes[i] = new GameShape(10, ShapeType.CIRCLE, x, y, 20, 20);
			_playerShapes[i].set_shapeID(i++);
		}
	}

	private void printPlayers()
	{
		System.out.print("Server broadcast: ");

		for(GameShape player: _playerShapes)
		{
			System.out.println(player.toString());
		}
		System.out.println("***********************************");
	}

	@Override
	public void run() {
		try {

			InetAddress groupAddress = InetAddress.getByName("228.0.0.4");



			ObjectOutputStream os =  null;
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
			BufferedOutputStream bos = new BufferedOutputStream(byteStream);

			//os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			while (isServing)
			{
				byteStream.reset();// had to get new objects sent each time...

				os = new ObjectOutputStream(bos);
				setRandomPlayerShapes();//Keep testing sending random new coordinates of players
				printPlayers();


				os.writeUnshared(_playerShapes);
				//os.reset(); //turns out to not be necessary
				os.flush();



				byte[] sendBuf = byteStream.toByteArray();
				DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, groupAddress, 8888);

				int byteCount = packet.getLength();

				socket.send(packet);


				try{
					Thread.sleep(1000);
				}catch(Exception e){}

			}//end while

			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			isServing = false;
		}
	}
}

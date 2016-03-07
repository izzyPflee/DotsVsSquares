package Server;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

import Renderer.GameShape;
import Renderer.ShapeType;

public class ServerWorkerThread extends Thread
{
	private ServerThreadPool _threadPool;
	private final int _id;

	public ServerWorkerThread(ServerThreadPool pool, int id)
	{
		_threadPool = pool;
		_id = id;
	}

	@Override
	public void run()
	{
		Random rand = new Random();
		try{
			InputStream clientInput = null;
			int keycode;
			while(! _threadPool.isServerKill())
			{

				Socket socket = _threadPool.getNewClient();
				_threadPool._gameShapes[_id] = new GameShape(20, ShapeType.SQUARE, rand.nextInt(800), rand.nextInt(800), 100, 100);
				GameShape shape = _threadPool._gameShapes[_id];
				clientInput = socket.getInputStream();

				while(socket.isConnected())
				{
					keycode = clientInput.read();
					shape.moveShape(keycode);
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

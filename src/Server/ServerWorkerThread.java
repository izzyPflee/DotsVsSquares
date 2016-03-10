package Server;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

import Renderer.GameBoard;
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
		int keycode = 0; 
		try{
			InputStream clientInput = null;
			
			
			while(! _threadPool.isServerKill())
			{

				Socket socket = _threadPool.getNewClient();
				
				System.out.println("Server worker #" + _id + " received a new client.");
				
				
				_threadPool._gameShapes[_id] = new GameShape(10, ShapeType.SQUARE, rand.nextInt(GameBoard.WORLD_BOUNDS) / 20, rand.nextInt(GameBoard.WORLD_BOUNDS) / 20, 20, 20);
				GameShape shape = _threadPool._gameShapes[_id];
				_threadPool._gameShapes[_id].set_shapeID(_id); //assign id to GameShape
				
				clientInput = socket.getInputStream();

				while(socket.isConnected())
				{
					keycode = clientInput.read();
					
					if(keycode == -1) //If disconnected signal received
					{
						_threadPool._gameShapes[_id] = null;
						socket.close();
						break;
					}
					
					shape.moveShape(keycode);
					
				}
				
				System.out.println("client #" + _id + " disconnected");
			}
		}
		catch(IOException e)
		{
			_threadPool._gameShapes[_id] = null;
			//socket.close();
			System.out.println("Client disconnected from worker...");
			//e.printStackTrace();
		}
	}
}

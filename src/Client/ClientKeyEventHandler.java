
package Client;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientKeyEventHandler {
	
	//private Socket socket;
	private PrintWriter out;
	
	public ClientKeyEventHandler(PrintWriter out) throws IOException
	{
		//this.socket = socket;
		
		//out = new PrintWriter(socket.getOutputStream());
		this.out = out;
	}
	
	public void processKey(KeyEvent input)
	{
		int key = input.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_UP:{} //move up

		case KeyEvent.VK_W:{} //move up

		case KeyEvent.VK_DOWN:{} //move down

		case KeyEvent.VK_S:{} //move down

		case KeyEvent.VK_LEFT:{} //move left

		case KeyEvent.VK_A:{} //move left

		case KeyEvent.VK_RIGHT:{} //move right

		case KeyEvent.VK_D:{} //move right

		case KeyEvent.VK_Q: // client quitting disconnect 
		{
			//System.out.println("THIS SHOULD CRASH NOW -- we need to add a listener on the server side");
			out.write(key);
			out.flush();
			break;
		}

		}			
	}
}

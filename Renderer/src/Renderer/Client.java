
public class Client 
{	
	/*
	 * start kb scanner thread
	 * start renderer
	 * 
	 * open network connection to the server for the game
	 * start network thread
	 * 
	 * 
	 * do loop
	 * 	>client gets data from the kb, 
	 * 		>it processes and sends request to the server
	 * 		>client should not wait for input if the server is updating the screen
	 * 	>client gets responce from the server for a responce from the server,
	 * 		>us server is slow the client should go back and check kb for input
	 * 	>client uses the renderers update method to updat the rendered
	 * 	>>if(server is dead,exit loop)
	 * loop sarts again.
	 * 
	 * kill renderer
	 * kill kbscanner thread
	 * 
	 * end progran
	 */
	
	public static void main(String[] args)
	{
		//start up threads
		System.out.println("hello Olin");
		//start main game loop
	}
	
	public static void gameLoop()
	{
		
	}
}

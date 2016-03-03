
/*
ClientGameStateParser Protocol:

The client expects to always receive a byte array of 20 bytes from
the Server. The 20 bytes represent 10 possible player coordinates (x,y)
as integer values. This limits the maximum player pool to 10.

The index pairs without a player are filled with -1, because it is outside of the Java canvas coordinates.

The players who needs to be rendered as squares are given values by the Server with
the maximum x and y of the canvas dimensions added to their coordinates. The client
subtracts these max values to reveal the actual position, and identify squares.

New clients are given an id during construction taking the first appearing -1 valued indexes in the broadcast byte array.
*/

public class ClientGameStateParser implements Runnable
{

	@Override
	public void run() 
	{
		// TO DO
		
	}

}

package Client;
import java.util.ArrayList;

import Renderer.GameShape;
/*
This file is deprecated and needs to go away forever.
 */

public class ClientGameStateParser 
{

	public static ArrayList<GameShape> parse(byte [] gameState)
	{
		ArrayList<GameShape> newListOfGameShapesEverytime = new ArrayList<>();

		for(int i = 0; i < gameState.length; i+=5)// Need to fix
		{
			int id = gameState[i];
			int x = parseCoordinate(gameState[i + 1], gameState[i + 2]);
			int y = parseCoordinate(gameState[i + 3], gameState[i + 4]);

		}

		return newListOfGameShapesEverytime;
	}

	private static int parseCoordinate(byte topByte, byte botByte)
	{
		int topInt = topByte  & (0xff);
		int botInt = botByte  & (0xff);
		int integer = (topInt << 8) | botInt;
		return integer;
	}

	private static int parseID(byte b)
	{
		int topNibble = b >> 4;
		topNibble &= 0xf;
		return topNibble;
	}

	private static int parseShapeType(byte b)
	{
		int bottomNibble = b & 0xf;
		return bottomNibble;
	}
	
	
	
	
}
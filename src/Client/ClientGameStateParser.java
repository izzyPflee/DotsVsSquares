package Client;
import java.util.ArrayList;

import Renderer.GameShape;
/*
This file is deprecated and needs to go away forever.
 */
import Renderer.ShapeType;

public class ClientGameStateParser 
{

	public static ArrayList<GameShape> parse(byte [] gameState)
	{
		ArrayList<GameShape> gameShapes = new ArrayList<>();

		for(int i = 0; i < gameState.length; i+=5)
		{
			int id = gameState[i];
			int x = parseCoordinate(gameState[i + 1], gameState[i + 2]);
			int y = parseCoordinate(gameState[i + 3], gameState[i + 4]);
			gameShapes.add(new GameShape(1, ShapeType.SQUARE, x, y, 10, 10));
		}

		return gameShapes;
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
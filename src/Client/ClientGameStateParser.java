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
			int isAlive = parseIsAlive(gameState[i]);
			
			if(isAlive == 1)
			{
				int id = parseID(gameState[i]);
				int x = parseCoordinate(gameState[i + 1], gameState[i + 2]);
				int y = parseCoordinate(gameState[i + 3], gameState[i + 4]);
				
				GameShape shape = new GameShape(10, ShapeType.SQUARE, x, y, 20, 20);
				shape.set_shapeID(id);
				gameShapes.add(shape);
			}
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
		return b & 0x0F;
	}

	private static int parseIsAlive(byte b)
	{
		return (b & 0xF0) >> 4;
	}
}
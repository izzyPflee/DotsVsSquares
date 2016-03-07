package RendererTests;

import javax.swing.*;

import Renderer.GameShape;
import Renderer.GameBoard;
import Renderer.ShapeType;
import Renderer.Renderer;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

/**
 * JFrame constantly closes in normal test methods, so
 * this class is used to test the JFrame and Renderer
 */
public class GameBoardTest extends JFrame implements KeyListener{

    public static void main(String[] args) {


        new GameBoardTest();

    }

    public GameBoardTest(){
        Renderer r = new Renderer(800,800, this);


        //add list of coordinates for rendering
        List<GameShape> gameShapes = new LinkedList<GameShape>();

        GameShape shape1 = new GameShape(10, ShapeType.SQUARE, 5,15,10,10);
        GameShape shape2 = new GameShape(10, ShapeType.SQUARE, 15,5,10,10);

        gameShapes.add(shape1);
        gameShapes.add(shape2);

        r.update(gameShapes);

    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}


package RendererTests;

import Renderer.Renderer;
import Renderer.GameShape;
import Renderer.GameBoard;
import Renderer.ShapeType;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 * JFrame constantly closes in normal test methods, so
 * this class is used to test the JFrame and Renderer
 */
public class GameBoardTest {

    public static void main(String[] args) {


        createAndRenderGameBoard();

    }

    public static void createAndRenderGameBoard(){
        Renderer r = new Renderer(800,800);




        //add list of coordinates for rendering
        List<GameShape> gameShapes = new LinkedList<GameShape>();

        GameShape shape1 = new GameShape(10, ShapeType.SQUARE, 5,15,10,10);
        GameShape shape2 = new GameShape(10, ShapeType.SQUARE, 15,5,10,10);

        gameShapes.add(shape1);
        gameShapes.add(shape2);

        r.update(gameShapes);

    }

}

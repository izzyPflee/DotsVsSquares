package RendererTests;

import Renderer.GameBoard;
import Renderer.GamePoint;
import Renderer.Coordinate;

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
        GameBoard board = new GameBoard();
        System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("GameBoard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(board);
        f.setSize(500,700);
        f.setVisible(true);
        f.setResizable(false);


        //add list of coordinates for rendering
        List<Renderer.GameShape> coords = new LinkedList<Coordinate>();




    }

}

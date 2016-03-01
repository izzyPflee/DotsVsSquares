package Tests;

import Renderer.GameBoard;

import javax.swing.*;

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

    }

}

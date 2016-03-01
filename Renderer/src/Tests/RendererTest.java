package Tests;


import Renderer.GameBoard;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by Isaac on 2/29/2016.
 */
public class RendererTest {


    @Test
    public void testGameBoardDraw() throws Exception {

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
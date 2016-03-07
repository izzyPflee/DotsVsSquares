package Renderer;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Created by Isaac on 2/29/2016.
 */
public class Renderer{

    //grid dimensions
    private final int _gridX;
    private final int _gridY;

    private final GameBoard _gameBoard;

    private JFrame _jFrame;



    public Renderer(int gridX, int gridY, JFrame jFrame) {
        _gridX = gridX;
        _gridY = gridY;

        _jFrame = jFrame;

        _gameBoard = new GameBoard();
        initGameRendererWindow();


    }


    private void initGameRendererWindow(){
        System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
        JFrame f = _jFrame;
        //f = new JFrame("GameBoard");
        f.setName("GameField");
        f.setTitle("GameBoard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(_gameBoard);
        f.setSize(_gridX,_gridY);
        f.setVisible(true);
        f.setResizable(false);
    }
    public void update(List<GameShape> gameShapes){

        _gameBoard.updateObjects(gameShapes);
    }

}
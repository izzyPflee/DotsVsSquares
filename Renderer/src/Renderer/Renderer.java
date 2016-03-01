package Renderer;

import javax.swing.*;
import java.util.List;

/**
 * Created by Isaac on 2/29/2016.
 */
public class Renderer {

    //grid dimensions
    private final int _gridX;
    private final int _gridY;

    private final GameBoard _gameBoard;



    public Renderer(int gridX, int gridY) {
        _gridX = gridX;
        _gridY = gridY;

        _gameBoard = new GameBoard();

    }

    private void initGameRendererWindow(){
        System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("GameBoard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(_gameBoard);
        f.setSize(_gridX,_gridY);
        f.setVisible(true);
        f.setResizable(false);
    }
    public void update(List<Coordinate> playerCoordinates){

        _gameBoard.updateObjects(playerCoordinates);
    }
}

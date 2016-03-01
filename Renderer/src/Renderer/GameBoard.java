package Renderer;


import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

/**
 * Gameboard used from pong project
 */
public class GameBoard extends JPanel {


    private Rectangle[] _rectangles;

    public GameBoard() {

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLACK);

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintPaddle(g);
    }

    public void paintPaddle(Graphics g){


        if(_rectangles == null)
            return;

        for(Rectangle rect: _rectangles){
            g.setColor(Color.CYAN);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    //TODO: should we make new rectangles every frame update or keep array rectangles?

    public void updateObjects(List<Coordinate> coords){

        //allocate space for rectangles
        _rectangles = new Rectangle[coords.size()];

        int i = 0;

        for(Coordinate coord: coords){
            _rectangles[i++] = new Rectangle(coord.getPoint());
        }

        this.repaint();


    }




}



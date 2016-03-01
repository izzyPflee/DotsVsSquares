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

/**
 * Gameboard used from pong project
 */
public class GameBoard extends JPanel {


    Rectangle paddle1 = new Rectangle(150, 20);
    Rectangle paddle2 = new Rectangle(150, 20);
    private final int PIXEL_STEP = 20; // pixel number to move paddle for each key press
    private final int LEFT = 37; // keyCode for left arrow
    private final int RIGHT = 39; // keyCode for right arrow

    public GameBoard() {

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLACK);



    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintPaddle(g);
    }

    public void paintPaddle(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(paddle1.x, this.getHeight() - paddle1.height, paddle1.width, paddle1.height);
        g.setColor(Color.RED);
        g.fillRect(paddle2.x, 0, paddle2.width, paddle2.height);
        g.setColor(Color.BLACK);
        g.drawRect(paddle1.x, this.getHeight() - paddle1.height, paddle1.width, paddle1.height);
        g.drawRect(paddle2.x, paddle2.height, paddle2.width, paddle2.height);
    }
    //TODO: gameboard should paint components(circle of player) where coordinates indicate




}



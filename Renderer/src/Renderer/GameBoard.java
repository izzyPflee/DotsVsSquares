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


    public GameBoard() {

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLACK);
    }

    //TODO: gameboard should paint components(circle of player) where coordinates indicate




}



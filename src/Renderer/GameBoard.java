package Renderer;


import java.awt.*;


import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.List;

/**
 * Gameboard used from pong project
 */
public class GameBoard extends JPanel {


    private Shape[] _shapes;

    public GameBoard() {

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.BLACK);

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintShape(g);
    }

    public void paintShape(Graphics g){


        if(_shapes == null)
            return;

        for(Shape shape: _shapes){

            //get dimensions of the shape(in the shape of a rectangle)
            //see: Shape interface
            Rectangle rect = shape.getBounds();

            g.setColor(Color.CYAN);
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
            g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        }
    }

    //TODO: should we make new rectangles every frame update or keep array rectangles?

    public void updateObjects(List<GameShape> gameShapes){

        //allocate space for shape
        _shapes = new Shape[gameShapes.size()];

        for(int i = 0; i < gameShapes.size(); i++){
            GameShape curShape = gameShapes.get(i);
            //create rectangle object for square
            if(curShape.get_shapeType() == ShapeType.SQUARE){
                _shapes[i] = new Rectangle(curShape.get_x(),curShape.get_y(), curShape.get_w(), curShape.get_h());
            }

        }

        this.repaint();

    }




}



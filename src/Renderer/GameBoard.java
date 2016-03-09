package Renderer;


import java.awt.*;


import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.List;

/**
 * Gameboard used from pong project
 */
public class GameBoard extends JPanel
{
	public static final int WORLD_BOUNDS = 500;
	
    private List<GameShape> _shapes;

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

        for(GameShape shape: _shapes){

            //get dimensions of the shape(in the shape of a rectangle)
            //see: Shape interface
            Rectangle rect = shape.getBounds();

            g.setColor(getColor(shape.get_shapeID()));
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
            g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        }
    }


    public void updateObjects(List<GameShape> gameShapes){

    	_shapes = gameShapes;
  
        this.repaint();
    }

    //returns color based on assigned id
    public Color getColor(int id)
    {
    	switch(id)
    	{
    	
			case 0:
				return Color.GRAY;
			case 1:
				return Color.BLUE;
			case 2:
				return Color.PINK;
			case 3:
				return Color.YELLOW;
			case 4:
				return Color.ORANGE;
			case 5:
				return Color.MAGENTA;
			case 6:
				return Color.WHITE;
			case 7:
				return Color.CYAN;
			case 8:
				return Color.GREEN;
			case 9:
				return Color.RED;
			default:
				return Color.DARK_GRAY;
    	
    	}
    	
    }


}



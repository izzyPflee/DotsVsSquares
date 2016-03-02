package Renderer;

import java.awt.*;

/**
 * Created by isaac on 3/1/16.
 */
public class GamePoint implements Coordinate {

    private Point _point;


    public GamePoint(int x, int y){
        _point = new Point(x, y);
    }

    @Override
    public Point getPoint() {
        return _point;
    }

    @Override
    public void setPoint() {

    }
}

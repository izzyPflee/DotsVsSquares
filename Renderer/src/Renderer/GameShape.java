package Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import static java.awt.event.KeyEvent.*;

/**
 * Created by isaac on 3/2/16.
 */
public class GameShape implements Serializable{

    //members
    private int _shapeID;
    private int _moveSpeed;
    private ShapeType _shapeType;
    private int _x;
    private int _y;
    private int _w;
    private int _h;

    public GameShape(int moveSpeed, ShapeType shapeType, int x, int y, int w, int h){
        _moveSpeed = moveSpeed;
        _shapeType = shapeType;
        _x = x;
        _y = y;
        _w = w;
        _h = h;
    }

    //methods
    public boolean checkCollision(GameShape otherShape)
    {
        //variables used to check collision
        //check for half width and height in each direction
        double otherW= otherShape._w/2;
        double otherH = otherShape._h/2;

        //check boundary collisions
        if((_x <= (_x + otherW)) && (_x >= (_x - otherW))){
            if((_y <=(_y + otherH)) && (_y >= (_y - otherH)))
            {
                return true;
            }
        }

        return false;
    }
//    updates shape's x,y values based on move speed

    public void moveShape(KeyEvent e){

        int key = e.getKeyCode();
        switch(key){
            case VK_LEFT: _x -= _moveSpeed;
                break;
            case VK_RIGHT: _x += _moveSpeed;
                break;
            case VK_UP: _y += _moveSpeed;
                break;
            case VK_DOWN: _y -= _moveSpeed;
                break;
            default:
                break;
        }

    }

    //getters and setters


    public int get_shapeID() {
        return _shapeID;
    }

    public void set_shapeID(int _shapeID) {
        this._shapeID = _shapeID;
    }

    public int get_moveSpeed() {
        return _moveSpeed;
    }

    public void set_moveSpeed(int _moveSpeed) {
        this._moveSpeed = _moveSpeed;
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public int get_w() {
        return _w;
    }

    public void set_w(int _w) {
        this._w = _w;
    }

    public int get_h() {
        return _h;
    }

    public void set_h(int _h) {
        this._h = _h;
    }
}

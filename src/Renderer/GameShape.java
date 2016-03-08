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
        int otherX = otherShape._x;
        int otherY = otherShape._y;
        double otherHalfW= otherShape._w/2;
        double otherHalfH = otherShape._h/2;
        double halfW = _w/2;
        double halfH = _w/2;

        //check top right corner of otherShape

        if((otherX + otherHalfW <= _x + halfW) &&
                (otherX + otherHalfW >= _x - halfW) &&
                (otherY + otherHalfH<= _y + halfH) &&
                (otherY + otherHalfH>= _y - halfH))
            return true;

        //check bottom right corner of otherShape
        if((otherX + otherHalfW <= _x + halfW) &&
                (otherX + otherHalfW >= _x - halfW) &&
                (otherY - otherHalfH<= _y + halfH) &&
                (otherY - otherHalfH>= _y - halfH))
            return true;

        //check top left corner of otherShape
        if((otherX - otherHalfW <= _x + halfW) &&
                (otherX - otherHalfW >= _x - halfW) &&
                (otherY + otherHalfH<= _y + halfH) &&
                (otherY + otherHalfH>= _y - halfH))
            return true;

        //check bottom left corner of otherShape
        if((otherX - otherHalfW <= _x + halfW) &&
                (otherX - otherHalfW >= _x - halfW) &&
                (otherY - otherHalfH<= _y + halfH) &&
                (otherY - otherHalfH>= _y - halfH))
            return true;


        return false;
    }
//    updates shape's x,y values based on move speed

    public void moveShape(int key){

        switch(key){
            case VK_LEFT: _x -= _moveSpeed;
                break;
            case VK_RIGHT: _x += _moveSpeed;
                break;
            case VK_UP: _y -= _moveSpeed;
                break;
            case VK_DOWN: _y += _moveSpeed;
                break;
            default:
                break;
        }

    }

    //getters and setters


    public int get_shapeID() {
        return _shapeID;
    }

    public void set_shapeID(int shapeID) {
        this._shapeID = shapeID;
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

    
    @Override
    public String toString()
    {
    	  StringBuilder sb = new StringBuilder();
    	  sb.append("Shape: id=" + _shapeID);
    	  sb.append(" speed=" +  _moveSpeed);
    	  sb.append(" type=" + _shapeType);
    	  sb.append(" x=" + _x);
    	  sb.append(" y=" + _y);
    	  sb.append(" width=" + _w);
    	  sb.append(" height=" + _h);
    	  return sb.toString();
    }

    public ShapeType get_shapeType() {
        return _shapeType;
    }

    public void set_shapeType(ShapeType _shapeType) {
        this._shapeType = _shapeType;
    }
    
    public Rectangle getBounds()
    {
    	return new Rectangle(get_x(), get_y(), get_w(), get_h());
    }
}

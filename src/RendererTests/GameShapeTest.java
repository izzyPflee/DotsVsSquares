package RendererTests;

import Renderer.GameShape;
import Renderer.ShapeType;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by isaac on 3/2/16.
 */
public class GameShapeTest {

//    @Before
//    public void setUp() throws Exception {
//
//    }

    @Test
    public void testCheckCollision() throws Exception {
        GameShape shape1 = new GameShape(10, ShapeType.SQUARE, 5,15,10,10);
        GameShape shape2 = new GameShape(10, ShapeType.SQUARE, 15,5,10,10);

        boolean val = shape1.checkCollision(shape2);
        System.out.println(val);


    }

//    @Test
//    public void testMoveShape() throws Exception {
//
//    }

//    @Test
//    public void checkObjectSerialization() throws Exception{
//
//        GameShape shape = new GameShape(10, ShapeType.SQUARE, 5,10,15,20);
//        FileOutputStream fout = new FileOutputStream("test");
//        ObjectOutputStream oOut = new ObjectOutputStream(fout);
//
//        oOut.writeObject(shape);
//        oOut.flush();
//
//    }
//
//    @Test
//    public void checkObjectDeserialization() throws Exception{
//
//        FileInputStream fIn = new FileInputStream("test");
//        ObjectInputStream oIn = new ObjectInputStream(fIn);
//
//        GameShape shape = (GameShape)oIn.readObject();
//
//        System.out.println("Done");
//
//
//    }
}
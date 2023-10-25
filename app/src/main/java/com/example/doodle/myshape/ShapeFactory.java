package com.example.doodle.myshape;

public class ShapeFactory {
    public static Shape createShape(int shapeID) {
        switch ( shapeID)
        {
            case 0: return new MyLine();
            case 1: return new MyEllipse();
            case 2: return new MyCircle();
        }
        return new MyLine();
    }
}

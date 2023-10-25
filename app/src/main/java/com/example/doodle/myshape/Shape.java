package com.example.doodle.myshape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;

public abstract class Shape {
    public Point P1, P2;
    public int  penColor = (255 << 24) | (255 <<16),
            brushColor = Color.WHITE;

    static public int encodeColor(int a, int r, int g, int b)
    {
        int c;
        c = (a << 24) | (r << 16) | (g << 8) | b;
        return c;
    }

    public void draw(Canvas canvas) {

    }
}

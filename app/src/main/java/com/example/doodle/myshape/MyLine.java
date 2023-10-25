package com.example.doodle.myshape;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyLine extends Shape {
    @Override
    public void draw(Canvas canvas) {
        Paint paint =new Paint();
        paint.setColor(penColor);
        paint.setStrokeWidth(3);
        canvas.drawLine(P1.x, P1.y, P2.x, P2.y, paint);
        //super.draw(canvas);
    }
}

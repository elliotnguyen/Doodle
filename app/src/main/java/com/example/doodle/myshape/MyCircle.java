package com.example.doodle.myshape;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyCircle extends Shape {
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(penColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        double radius = Math.sqrt((P1.x - P2.x) * (P1.x - P2.x) + (P1.y - P2.y) * (P1.y - P2.y));
        canvas.drawCircle(P1.x, P1.y, (float) radius, paint);
        //super.draw(canvas);
    }
}

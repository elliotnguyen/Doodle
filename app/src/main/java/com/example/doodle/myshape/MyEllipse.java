package com.example.doodle.myshape;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MyEllipse extends Shape {
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(penColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        //paint.setStrokeWidth(1);
        canvas.drawOval(P1.x, P1.y, P2.x, P2.y, paint);
    }
}

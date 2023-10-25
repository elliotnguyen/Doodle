package com.example.doodle.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.doodle.GlobalSettings;
import com.example.doodle.myshape.Shape;
import com.example.doodle.myshape.ShapeFactory;

import java.util.ArrayList;

public class MyPaintView extends View {
    private boolean bDraw = false;
    private Bitmap memDC;
    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        memDC = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(memDC);
        canvas.drawARGB(255, 255, 255, 255);
    }

    public void drawCircle(ArrayList<Shape> shapes) {
        bDraw= true;
        this.shapes = shapes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(memDC, 0, 0, null);

        if (bDraw)
            shapes.get(shapes.size()-1).draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maskedAction = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                beginDraw(x, y);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was `move`d
                if (bDraw)
                    processDraw(x, y);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                if (bDraw)
                    endDraw(x, y);
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
        }
        return true;
    }
    private void drawOnMemDCForMePlease() {
        Canvas memCanvas = new Canvas(memDC);
        memCanvas.drawARGB(255, 255, 255, 255);
        for (int i=0; i<shapes.size(); i++)
            shapes.get(i).draw(memCanvas);
    }

    public MyPaintView(Context context) {
        super(context);
    }
    public MyPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void endDraw(float x, float y) {
        bDraw = false;
        processDraw(x,y);
        drawOnMemDCForMePlease();
    }

    private void processDraw(float x, float y) {
        Shape obj = shapes.get(shapes.size()-1);
        obj.P2 = new Point((int)x, (int)y);
        invalidate();
    }

    private void beginDraw(float x, float y) {
        bDraw = true;
        Shape obj = ShapeFactory.createShape(GlobalSettings.ShapeID);
        obj.P1 = new Point((int)x, (int)y);
        obj.P2 = obj.P1;
        obj.penColor = GlobalSettings.PenColor;
        //obj.brushColor = GlobalSettings.BrushColor;
        shapes.add(obj);
        invalidate();
    }
}

package com.example.doodle.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.doodle.R;
import com.example.doodle.myshape.MyCircle;
import com.example.doodle.myshape.MyLine;
import com.example.doodle.myshape.Shape;

import java.util.ArrayList;
import java.util.List;

public class MyDoodleView extends View {
    private Bitmap memDC, previousDC;
    private boolean isDisplayedText = false;
    private int screenWidth = 1024;
    private int screenHeight = 1024;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        memDC = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }

    MyTextView myTextView = new MyTextView(getContext(), "i love you", 20);

    public void displayShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();

        Shape line = new MyLine();
        line.P1 = new Point(0, (screenHeight / 5) * 4 );
        line.P2 = new Point(screenWidth, (screenHeight / 5) * 4);
        //line.draw(canvas);
        shapes.add(line);

        for (int i = 0; i < 3; i++) {
            Shape circle = new MyCircle();
            circle.P1 = new Point(screenWidth / 2, (screenHeight / 5) * 4);
            circle.P2 = new Point(screenWidth / 2 + i * 20 + 100, (screenHeight / 5) * 4);
            shapes.add(circle);
        }

        Canvas canvas = new Canvas(memDC);
        canvas.drawRGB(255, 255, 255);
        for (int i=0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas);

        for (int i = 0; i < currentPoint; i++) {
            Rect target = new Rect(
                    heartPoints.get(i).x,
                    heartPoints.get(i).y,
                    heartPoints.get(i).x + 100,
                    heartPoints.get(i).y + 100);
            canvas.drawBitmap(img, null, target, null);
        }
    }

    public void displayText() {
        previousDC = memDC;
        myTextView.setTextColor(Color.RED);
        myTextView.setTypeface(myTextView.getTypeface(), Typeface.BOLD);

        Canvas canvas = new Canvas(memDC);
        myTextView.customizedDraw(canvas, currentCoordX,(screenHeight / 5) * 4);
    }
    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    private List<Point> heartPoints = generateHeart(screenWidth, screenHeight);
    private int currentPoint = 0;
    private int currentCoordX = 0;
    private Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.love_always_wins);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        displayShapes();
        canvas.drawBitmap(memDC, 0, 0, null);

        if (isDisplayedText) {
            displayText();
            canvas.drawBitmap(memDC, 0, 0, null);
            memDC = previousDC;
        }
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    public void moveText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isDisplayedText = true;
                invalidate();
                currentCoordX += 50; // Move to the next point
                if (currentCoordX + myTextView.get_width() <= screenWidth) {
                    // Continue the animation
                    moveText();
                }
            }
        }, 100);
    }

    public void spinnerAround() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
                currentPoint++; // Move to the next point
                if (currentPoint < heartPoints.size()) {
                    // Continue the animation
                    spinnerAround();
                }
            }
        }, 100);
    }

    public List<Point> generateHeart(int width, int height) {
        List<Point> points = new ArrayList<>();
        int centerX = width / 2;
        int centerY = (height / 5) * 3;

        int numPoints = 50; // Adjust the number of points for smoother or coarser heart shape

        for (int i = 0; i < numPoints; i++) {
            double t = 2 * Math.PI * i / numPoints;
            double x = 16 * Math.pow(Math.sin(t), 3);
            double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t);

            int xPixel = (int) (centerX + x * 30); // Scale factor for better visibility
            int yPixel = (int) (centerY - y * 30); // Scale factor for better visibility

            points.add(new Point(xPixel, yPixel));
        }

        return points;
    }

    public MyDoodleView(Context context) {
        super(context);
    }

    public MyDoodleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDoodleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyDoodleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

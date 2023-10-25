package com.example.doodle.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.doodle.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyAnimationView extends View {
    private int selectedIndex = -1;
    private float oldX;
    private float oldy;
    private int screenWidth = 1024;
    private int screenHeight  = 1024;

    public MyAnimationView(Context context) {
        super(context);
        prepareContent();
    }

    public MyAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepareContent();
    }
    private ArrayList<My2DSprite> sprites;

    private Timer timer;
    private TimerTask timerTask;

    private void prepareContent() {
        sprites = new ArrayList<>();
        createIsland(100, 100, R.drawable.starter_island01);
        createBuilding(50, 150, R.drawable.kindergarten01);
        for (int i=0; i<5; i++)
            createAngel(100+i*30, 100+i*2);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i=0; i<sprites.size(); i++)
                    sprites.get(i).update();

                postInvalidate();
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 1000, 40);
    }

    private void createAngel(int left, int top) {
        Bitmap[] bmps = new Bitmap[15];
        bmps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.angel01);
        bmps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.angel02);
        bmps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.angel03);
        bmps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.angel04);
        bmps[4] = BitmapFactory.decodeResource(getResources(), R.drawable.angel05);
        bmps[5] = BitmapFactory.decodeResource(getResources(), R.drawable.angel06);
        bmps[6] = BitmapFactory.decodeResource(getResources(), R.drawable.angel07);
        bmps[7] = BitmapFactory.decodeResource(getResources(), R.drawable.angel08);
        bmps[8] = BitmapFactory.decodeResource(getResources(), R.drawable.angel09);
        bmps[9] = BitmapFactory.decodeResource(getResources(), R.drawable.angel10);
        bmps[10] = BitmapFactory.decodeResource(getResources(), R.drawable.angel11);
        bmps[11] = BitmapFactory.decodeResource(getResources(), R.drawable.angel12);
        bmps[12] = BitmapFactory.decodeResource(getResources(), R.drawable.angel13);
        bmps[13] = BitmapFactory.decodeResource(getResources(), R.drawable.angel14);
        bmps[14] = BitmapFactory.decodeResource(getResources(), R.drawable.angel15);
        My2DSprite newSprite = new My2DSprite(bmps, left, top, 0, 0);
        sprites.add(newSprite);

    }

    private void createBuilding(int left, int top, int resID) {
        createSpriteWithASingleImage(left, top, resID);
    }

    private void createIsland(int left, int top, int resID) {
        createSpriteWithASingleImage(left, top, resID);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
    }

    private void createSpriteWithASingleImage(int left, int top, int resID) {
        Bitmap[] bmps = new Bitmap[1];
        bmps[0] = BitmapFactory.decodeResource(getResources(), resID);
        My2DSprite newSprite = new My2DSprite(bmps, left, top, 0, 0);
        sprites.add(newSprite);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //        super.onDraw(canvas);
        renderAll(); // flicker-free
        canvas.drawBitmap(bmpAll, 0, 0, null);

//        canvas.drawRGB(255, 255, 255);
//        for (int i=0; i<sprites.size(); i++)
//            sprites.get(i).draw(canvas);
    }

    private Bitmap bmpAll = null;
    private void renderAll() {
        bmpAll = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpAll);
        canvas.drawRGB(255, 255, 255);
        for (int i=0; i<sprites.size(); i++)
            sprites.get(i).draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maskedAction = event.getActionMasked();

        float x = event.getX();
        float y = event.getY();
        int tempIdx;
        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:{
                // TODO use data
                tempIdx = getSelectedSpriteIndex(x, y);
/*                if (tempIdx!=-1) {
                    selectSprite(tempIdx);
                    invalidate();
                }*/
                beginDrag(x, y);

                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                // TODO use data
                processDrag(x, y);

                break;
            }
            case MotionEvent.ACTION_UP:            {
                endDrag(x, y);

                break;
            }
        }

        return true;
    }

    private void endDrag(float x, float y) {
        processDrag(x,y);
        selectedIndex = -1;
    }

    private void processDrag(float x, float y) {
        float dx = x - oldX;
        float dy = y - oldy;
        if (selectedIndex!=-1)
        {
            sprites.get(selectedIndex).left+=dx;
            sprites.get(selectedIndex).top+=dy;
            invalidate();
        }
        oldX = x;
        oldy = y;
    }

    private void beginDrag(float x, float y) {
        int tempIdx = getSelectedSpriteIndex(x, y);
        if (tempIdx!=-1) {
            selectedIndex = tempIdx;
            oldX = x;
            oldy = y;

            selectSprite(selectedIndex);
/*            if (onSpriteClickListener!=null)
            {
                onSpriteClickListener.OnSpriteClick(this, x, y, selectedIndex);
            }*/
            invalidate();
        }
        else selectedIndex = -1;

    }

    private void selectSprite(int newIdx) {
        for (int i=0;  i<sprites.size(); i++)
        {
            sprites.get(i).State = newIdx == i;
        }
    }

    private int getSelectedSpriteIndex(float x, float y) {
        for (int i=sprites.size()-1;i>=0; i--)
            if (sprites.get(i).isSelected(x, y))
                return i;
        return -1;
    }
}

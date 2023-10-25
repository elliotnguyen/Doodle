package com.example.doodle.customview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class My2DSprite {
    public int nBMPs;
    public int iBMP;
    public Bitmap[] BMPs;
    public int left;
    public int top;
    public int width;
    public int height;
    public boolean State = false;

    public My2DSprite(Bitmap[] bmps, int left, int top, int width, int height)
    {
        BMPs = bmps;
        nBMPs = bmps.length;
        iBMP = 0;
        this.left = left;
        this.top = top;
        if (width == 0 && height==0)
        {
            width = bmps[0].getWidth();
            height = bmps[0].getHeight();
        }
        this.width = width;
        this.height = height;
    }

    private float d1 = 0;
    private float d2 = 1;
    public void update()
    {
        iBMP = (iBMP+1) % nBMPs;
        if (State)
        {
            d1 += d2;
            if (Math.abs(d1)>=10)
                d2*=-1;
        }
    }

    public void draw(Canvas canvas)
    {
        if (State)
        {
            Rect source;
            Rect target;
            source = new Rect(0, 0, width-1, height-1);
            target = new Rect(
                    (int)(left-d1),
                    (int)(top-d1),
                    (int)(left+width-1+d1),
                    (int)(top+height-1+d1));
            canvas.drawBitmap(BMPs[iBMP], source, target, null);
        }
        else
            canvas.drawBitmap(BMPs[iBMP], left, top, null);
    }


    public boolean isSelected(float x, float y) {
        return (x>=left && x < left+width && y>=top && y <top+height);
    }
}

package com.example.doodle.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MyTextView extends AppCompatTextView {
    private int _width, _height;
    private final Rect _bounds = new Rect();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // vise versa
        _height = getMeasuredWidth();
        _width = getMeasuredHeight();
        setMeasuredDimension(_width, _height);
    }

    public void customizedDraw(Canvas canvas, int start, int baseLine) {
        TextPaint paint = getPaint();
        paint.setColor(getTextColors().getDefaultColor());

        String text = text();

        paint.getTextBounds(text, 0, text.length(), _bounds);
        canvas.drawText(text, start, baseLine, paint);
        //canvas.drawText(text, , (_bounds.height() - _width) / 2, paint);
    }

    public int get_width() {
        return _bounds.width();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private String text() {
        return super.getText().toString();
    }

    public MyTextView(Context context, String text, float textSize) {
        this(context, text, textSize, Color.BLACK);
    }

    public MyTextView(Context context, String text, float textSize, int textColor) {
        super(context);
        setText(text);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        setTextColor(textColor);
    }
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
    }
}

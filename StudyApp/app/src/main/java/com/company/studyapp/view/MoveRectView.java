package com.company.studyapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class MoveRectView extends View {

    private final Paint mPaint;
    private Rect rect;
    private Context context;

    public MoveRectView(Context context) {
        this(context, null);
    }

    public MoveRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 自定义view的测量方法
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 设置view的宽高
        setMeasuredDimension(widthSize,heightSize);
    }

    /**
     * 绘制
     *
     * @param canvas canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rect == null) {
            return;
        }
        canvas.drawRect(rect,mPaint);
    }

    public synchronized void setRect(int left, int top, int right, int bottom) {

        if (rect == null) {
            rect = new Rect(left,top,right,bottom);
        } else {
            rect.set(left,top,right,bottom);
        }
        invalidate();
    }

}

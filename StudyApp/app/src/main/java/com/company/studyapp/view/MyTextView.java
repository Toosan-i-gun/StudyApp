package com.company.studyapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.company.studyapp.R;
import com.company.studyapp.utils.BaseUtils;

/**
 * 自定一个自己的View
 * 实现三个构造方法
 * 前两个调this,最后一个调super
 * 保证最后都调到第三个方便管理
 */
class MyTextView extends View {
    private static final String TAG = "MyTextView";

    private String mTextStr;
    private int mTextSize = 15;
    private int mTextColor = Color.RED;

    private Paint mPaint;

    /**
     * 这个构造函数是在代码中new出来的时候调用
     * MyTextView textView = new MyTextView(context);
     *
     * @param context context
     */
    public MyTextView(Context context) {
        this(context, null);
    }

    /**
     * 在布局中使用的时候会调用
     * <com.company.studyapp.view.MyTextView
     * android:text="@string/app_name"
     * android:layout_width="wrap_content"
     * android:layout_height="wrap_content"/>
     *
     * @param context context
     * @param attrs   attrs
     */
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 设计自己定制的一些样式时会调用
     * <com.company.studyapp.view.MyTextView
     * style="@style/my_text_view_default"
     * android:text="@string/app_name"/>
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     */
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_my_textSize, BaseUtils.sp2px(context,mTextSize));
        mTextColor = array.getColor(R.styleable.MyTextView_my_textColor, mTextColor);
        mTextStr = array.getString(R.styleable.MyTextView_my_text);
        if (TextUtils.isEmpty(mTextStr)) {
            mTextStr = "";
        }
        // 用完后要回收
        array.recycle();
        mPaint = new Paint();
        // 设置抗锯齿  可以清楚一点
        mPaint.setAntiAlias(true);
        // 设置画笔的大小和颜色
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        Log.i(TAG,"mTextSize = "+mTextSize);
        Log.i(TAG,"mTextColor = "+mTextColor);
        Log.i(TAG,"mTextStr = "+mTextStr);
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

        // 如果是wrap_content,那么需要进行计算,与text的字体大小和内容长度有关
        if (widthMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            // 测量 text的内容
            mPaint.getTextBounds(mTextStr,0,mTextStr.length(),rect);
            // 把测量后的大小进行赋值
            widthSize = rect.width();
            // 还需要考虑padding
            widthSize = widthSize + getPaddingStart() + getPaddingEnd();
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            Rect rect = new Rect();
            // 测量 text的内容
            mPaint.getTextBounds(mTextStr,0,mTextStr.length(),rect);
            // 把测量后的大小进行赋值
            heightSize = rect.height();
            // 还需要考虑padding
            heightSize = heightSize + getPaddingBottom() + getPaddingTop();
        }
        // 设置view的宽高
        setMeasuredDimension(widthSize,heightSize);
    }

    /**
     * 绘制
     *
     * @param canvas canvas
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * 基线到底部的距离 fontMetricsInt.bottom
         * 基线到顶部的距离 fontMetricsInt.top
         * 中间的位置(fontMetricsInt.bottom - fontMetricsInt.top) / 2
         */
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int hy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        canvas.drawText(mTextStr,getPaddingStart(),getHeight()/2 + hy,mPaint);
    }

    /**
     * 事件分发
     *
     * @param event event
     * @return boolean
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "手指按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "手指滑动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "手指抬起");
                break;
        }
        return true;
    }
}

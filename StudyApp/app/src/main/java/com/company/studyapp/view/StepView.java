package com.company.studyapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.company.studyapp.R;
import com.company.studyapp.utils.BaseUtils;

public class StepView extends View {

    private float mMaxStep = 5000;

    private int mOuterColor;
    private int mInnerColor;
    private int mTextSize = 18;
    private float mBorderWidth = 6;
    private int mTextColor;
    private String mTextContent;

    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextpaint;
    private int currentStep;


    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        mOuterColor = array.getColor(R.styleable.StepView_outer_color, context.getResources().getColor(R.color.purple_700));
        mInnerColor = array.getColor(R.styleable.StepView_inner_color, context.getResources().getColor(R.color.purple_200));
        mTextSize = array.getDimensionPixelSize(R.styleable.StepView_text_size, BaseUtils.sp2px(context, mTextSize));
        mBorderWidth = array.getDimension(R.styleable.StepView_border_width, mBorderWidth);
        mTextColor = array.getColor(R.styleable.StepView_text_color, Color.RED);
        mTextContent = array.getString(R.styleable.StepView_text_content);
        array.recycle();
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth); // 设置画笔大小
        mInnerPaint.setStyle(Paint.Style.STROKE); // 设置画笔样式 填充、中空...
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND); // 画笔边缘样式

        mTextpaint = new Paint();
        mTextpaint.setAntiAlias(true);
        mTextpaint.setColor(mTextColor);
        mTextpaint.setTextSize(mTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if (wMode == MeasureSpec.AT_MOST || hMode == MeasureSpec.AT_MOST) {
            width = 40;
            height = 40;
        }
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(mTextContent)) {
            return;
        }
        int center = getWidth() / 2;
        int ri = (int) (center - mBorderWidth / 2);
        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, ri * 2 + mBorderWidth / 2, ri * 2 + mBorderWidth / 2);
//        currentStep = Integer.parseInt(mTextContent);
        float angle = Math.min(currentStep / mMaxStep * 270, 270);
        canvas.drawArc(rectF, 135, 270, false, mOutPaint);
        canvas.drawArc(rectF, 135, angle, false, mInnerPaint);
        Paint.FontMetricsInt fontMetricsInt = mTextpaint.getFontMetricsInt();
        int hy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        Rect rect = new Rect();
        // 测量 text的内容
        mTextpaint.getTextBounds(mTextContent, 0, mTextContent.length(), rect);
        canvas.drawText(mTextContent, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + hy, mTextpaint);
//        canvas.drawRect(rectF,mOutPaint);
    }

    public synchronized void setMaxStep(float mMaxStep) {
        this.mMaxStep = mMaxStep;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
        mTextContent = String.valueOf(currentStep);
        // 重绘
        invalidate();
    }
}

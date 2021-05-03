package com.company.studyapp.view;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定一个自己的View
 * 实现三个构造方法
 */
class MyTextView extends View {
    /**
     * 这个构造函数是在代码中new出来的时候调用
     * MyTextView textView = new MyTextView(context);
     * @param context context
     */
    public MyTextView(Context context) {
        super(context);
    }

    /**
     * 在布局中使用的时候会调用
     *  <com.company.studyapp.view.MyTextView
     *         android:text="@string/app_name"
     *         android:layout_width="wrap_content"
     *         android:layout_height="wrap_content"/>
     * @param context context
     * @param attrs attrs
     */
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设计自己定制的一些样式时会调用
     *  <com.company.studyapp.view.MyTextView
     *         style="@style/my_text_view_default"
     *         android:text="@string/app_name"/>
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     */
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 自定义view的测量方法
     * @param widthMeasureSpec widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       int mode = MeasureSpec.getMode(widthMeasureSpec);
       if (mode == MeasureSpec.AT_MOST) {

       }
    }
}

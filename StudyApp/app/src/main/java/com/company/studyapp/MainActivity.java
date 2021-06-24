package com.company.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.company.studyapp.view.MoveRectView;
import com.company.studyapp.view.StepView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStepView();
    }



    private void initStepView() {
        StepView stepView = findViewById(R.id.step_view);
        stepView.setMaxStep(8000);
        // 设置动画 ofFloat 动画变动区间
        ValueAnimator animator = ValueAnimator.ofFloat(0,5000);
        // 动画从开始到结束的时间
        animator.setDuration(1500);
        // 不断获取变化区间改变时的值
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float step = (float) animation.getAnimatedValue();
                stepView.setCurrentStep((int)step);
            }
        });
        // 执行动画
        animator.start();
    }
}
package com.company.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.company.studyapp.view.StepView;

public class MainActivity extends AppCompatActivity {

    private StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepView = findViewById(R.id.step_view);
        stepView.setMaxStep(8000);
        // 设置动画 ofFloat 动画变动区间
        ValueAnimator animator = ObjectAnimator.ofFloat(0,5000);
        // 动画从开始到结束的时间
        animator.setDuration(5000);
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
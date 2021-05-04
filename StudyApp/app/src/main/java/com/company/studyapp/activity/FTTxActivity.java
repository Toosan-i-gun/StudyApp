package com.company.studyapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.company.studyapp.R;
import com.company.studyapp.adapter.FTTxAdapter;
import com.company.studyapp.bean.RectVo;
import com.company.studyapp.constants.Constant;
import com.company.studyapp.utils.BaseUtils;
import com.company.studyapp.view.MoveRectView;

import java.util.ArrayList;
import java.util.List;

public class FTTxActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> dataList;
    private Handler mHandler;
    private int times = 1;
    private MoveRectView rectView;
    private RectVo currentRect;
    private int cCenterX;
    private int cCenterY;
    private int nCenterX;
    private int nCenterY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fttx);
        listView = findViewById(R.id.list_view);
        initHandler();
        initData();
        initMoveRect();
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("name ------- " + i);
        }
        FTTxAdapter adapter = new FTTxAdapter(this, dataList, new FTTxAdapter.ItemClickCallBack() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(FTTxActivity.this, "click name" + position, Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(adapter);
    }

    private void initMoveRect() {
        rectView = findViewById(R.id.move_rect);
        rectView.setRect(times * 100, (times + 1) * 100, (times + 2) * 100, (times + 3) * 100);
        currentRect = new RectVo(times * 100, (times + 1) * 100, (times + 2) * 100, (times + 3) * 100);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                times++;
                mHandler.obtainMessage(Constant.FTTX_UPDATE_RECT,
                        new RectVo(times * 100, (times + 1) * 100, (times + 2) * 100, (times + 3) * 100)).sendToTarget();
            }
        }, 1000);
    }


    private void initHandler() {
        mHandler = new Handler(getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case Constant.FTTX_UPDATE_RECT:
                        if (times >= 7) {
                            mHandler.obtainMessage(Constant.FTTX_UPDATE_RECT_FAILED).sendToTarget();
                            break;
                        }
                        RectVo rectVo = (RectVo) msg.obj;
//                        cCenterX = (currentRect.getRight() - currentRect.getLeft()) / 2 + currentRect.getLeft();
//                        cCenterY = (currentRect.getBottom() - currentRect.getTop()) / 2 + currentRect.getTop();
//                        nCenterX = (rectVo.getRight() - rectVo.getLeft()) / 2 + rectVo.getLeft();
//                        nCenterY = (rectVo.getBottom() - rectVo.getTop()) / 2 + rectVo.getTop();
                        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(rectView, "translationY",0, BaseUtils.dp2px(FTTxActivity.this,rectVo.getBottom()-currentRect.getBottom()));
                        objectAnimatorY.setDuration(300);
                        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(rectView, "translationX",0,BaseUtils.dp2px(FTTxActivity.this,rectVo.getRight()-currentRect.getRight()));
                        objectAnimatorX.setDuration(300);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(objectAnimatorX,objectAnimatorY);
//                        animatorSet.start();
                        Message message = Message.obtain();
                        message.what = Constant.FTTX_UPDATE_RECT_START;
                        message.obj = rectVo;
                        mHandler.sendMessageDelayed(message,300);
                        break;
                    case Constant.FTTX_UPDATE_RECT_START:
                        RectVo rectVoS = (RectVo) msg.obj;
                        rectView = findViewById(R.id.move_rect);
                        rectView.setRect(rectVoS.getLeft(), rectVoS.getTop(), rectVoS.getRight(), rectVoS.getBottom());
                        currentRect = rectVoS;
                        times++;
                        Message messageS = Message.obtain();
                        messageS.what = Constant.FTTX_UPDATE_RECT;
                        messageS.obj = new RectVo(times * 100, (times + 1) * 100, (times + 2) * 100, (times + 3) * 100);
                        mHandler.sendMessageDelayed(messageS, 1000);
                        break;
                    case Constant.FTTX_UPDATE_RECT_FAILED:
                        Toast.makeText(FTTxActivity.this, "update failed.", Toast.LENGTH_LONG).show();
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }
}
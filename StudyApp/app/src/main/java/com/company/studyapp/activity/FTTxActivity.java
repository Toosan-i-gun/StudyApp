package com.company.studyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.company.studyapp.R;
import com.company.studyapp.adapter.FTTxAdapter;

import java.util.ArrayList;
import java.util.List;

public class FTTxActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fttx);
        listView = findViewById(R.id.list_view);
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("name ------- "+ i);
        }
        FTTxAdapter adapter = new FTTxAdapter(this, dataList, new FTTxAdapter.ItemClickCallBack() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(FTTxActivity.this,"click name"+position,Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(adapter);
    }

}
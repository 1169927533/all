package com.example.a11699.all.CustomLayoutManger;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a11699.all.CustomLayoutManger.Custom.custonmanager;
import com.example.a11699.all.R;

import java.util.ArrayList;
import java.util.List;

public class customLayoutManagerActivity extends AppCompatActivity {
    RecyclerView custom_recycleview;
    RecycleAdapter recycleAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_manager);
        initview();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        custonmanager cm = new custonmanager();
        custom_recycleview.setLayoutManager(cm);
        recycleAdapter = new RecycleAdapter(this, list);
        custom_recycleview.setAdapter(recycleAdapter);


    }

    void initview() {
        custom_recycleview = findViewById(R.id.custom_recycleview);
        list.add("我的測試1");
        list.add("我的測試2");
        list.add("我的測試3");
        list.add("我的測試4");
        list.add("我的測試5");
        list.add("我的測試6");
        list.add("我的測試7");
        list.add("我的測試8");
        list.add("我的測試9");
        list.add("我的測試10");
        list.add("我的測試11");
        list.add("我的測試12");
        list.add("我的測試13");
        list.add("我的測試14");
        list.add("我的測試15");
        list.add("我的測試16");
        list.add("我的測試17");
        list.add("我的測試18");
        list.add("我的測試19");
        list.add("我的測試20");
        list.add("我的測試21");
    }
}

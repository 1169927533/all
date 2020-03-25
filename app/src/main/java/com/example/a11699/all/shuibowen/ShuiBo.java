package com.example.a11699.all.shuibowen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a11699.all.R;

public class ShuiBo extends AppCompatActivity {
    RadarView radarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui_b);
        radarView = findViewById(R.id.radarView);
        //设置雷达扫描方向
        radarView.setDirection(RadarView.ANTI_CLOCK_WISE);
        radarView.start();
    }
}

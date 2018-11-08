package com.example.a11699.all.zidingyiView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.a11699.all.MainActivity;
import com.example.a11699.all.R;
import com.example.a11699.all.zidingyiView.View.ZheLine_View;

public class DrawZheLine extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actuvuty_dradzhexian);
       frameLayout=findViewById(R.id.fragmen);
        //ZheLine_View mezi = new ZheLine_View(DrawZheLine.this);
        initview();
    }
    void initview(){
        ZheLine_View clc = (ZheLine_View) findViewById(R.id.cl);
        clc.setLineColor(R.color.colorPink);
// 必须设置，还有 pointNum 在布局或代码设置至少有一个地方设置，且 pointNum 需等于 percents.length
        clc.setPercents(new float[]{0.5f, 0.3f, 0.4f, 0.7f, 0.1f, 0.2f, 0.6f, 0.8f, 0.9f, 1f});
       // frameLayout.addView(clc);
    }
}

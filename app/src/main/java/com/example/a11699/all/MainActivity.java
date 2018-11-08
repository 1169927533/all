package com.example.a11699.all;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a11699.all.gongxiang.gongxiangActivity;
import com.example.a11699.all.zidingyiView.DrawZheLine;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button main_gongxiang,drawZhexian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }
    void initview(){
        main_gongxiang=findViewById(R.id.main_gongxiang);
        drawZhexian=findViewById(R.id.drawZhexian);
        main_gongxiang.setOnClickListener(this);
        drawZhexian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_gongxiang:
                Intent intent=new Intent(MainActivity.this,gongxiangActivity.class);
                startActivity(intent);
                break;
            case R.id.drawZhexian:
                Intent intent1=new Intent(MainActivity.this, DrawZheLine.class);
                startActivity(intent1);
                break;
        }

    }
}

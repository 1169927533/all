package com.example.a11699.all.shengsuo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a11699.all.R;
import com.example.a11699.all.shengsuo.activity.activity.Shengsuo_1;
import com.example.a11699.all.shengsuo.activity.activity.Shensuo_2;

public class Xiaoguo_1 extends AppCompatActivity implements View.OnClickListener {

    Button shengsuo_one,shengsuo_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoguo_1);
        shengsuo_one = findViewById(R.id.shengsuo_one);
        shengsuo_two = findViewById(R.id.shengsuo_two);
        shengsuo_one.setOnClickListener(this);
        shengsuo_two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shengsuo_one:
                Intent intent = new Intent(this, Shengsuo_1.class);
                startActivity(intent);
                break;
            case R.id.shengsuo_two:
                Intent intent1 = new Intent(this, Shensuo_2.class);
                startActivity(intent1);
                break;
        }
    }
}

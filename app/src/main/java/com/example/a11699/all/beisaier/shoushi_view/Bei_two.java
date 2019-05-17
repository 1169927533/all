package com.example.a11699.all.beisaier.shoushi_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.a11699.all.R;

//手势下滑，弧线加大
//手势上滑，弧线变小
public class Bei_two extends AppCompatActivity implements View.OnClickListener {

    Zi_two_view zi_two_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_two);

        zi_two_view = findViewById(R.id.zi_two_view);


    }

//进行事件的分发


    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    //onInterceptTouchEvent主要进行事件的拦截

}

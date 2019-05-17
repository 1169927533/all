package com.example.a11699.all.fragment;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.a11699.all.MainActivity;
import com.example.a11699.all.R;
import com.example.a11699.all.fragment.View.MyView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimaFragment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.fragment_anima);
        FrameLayout frame = findViewById(R.id.mylayout);
        final MyView mezi = new MyView(this);
        //为我们的萌妹子添加触摸事件监听器
        mezi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //设置妹子显示的位置
                mezi.bitmapX = event.getX() - 150;
                mezi.bitmapY = event.getY() - 150;
                //调用重绘方法
                mezi.invalidate();

                return true;
            }
        });
        frame.addView(mezi);
    }


}

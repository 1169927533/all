package com.example.a11699.all.gongxiang;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;

import com.example.a11699.all.R;

public class gongxiangActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongxiang);
        initview();
    }
    void initview(){
        floatingActionButton=findViewById(R.id.fabbb);
        floatingActionButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabbb:
                /**
                 *  共享一个元素的时候
                 *
                 * 第一个参数  activity
                 * 第二个参数  View sharedElement 共享的元素
                 * 第三个参数  sharedElementName  在xml中指定的共享元素的名字
                 */
                Slide slide = new Slide();
                slide.setDuration(1000);
                getWindow().setExitTransition(slide);//Activity过渡动画
                getWindow().setEnterTransition(slide);//Activity过渡动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //这个是另外一种形式的过度动画
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(gongxiangActivity.this, floatingActionButton, floatingActionButton.getTransitionName());
                    startActivity(new Intent(gongxiangActivity.this, gongxiangtwoActivity.class), options.toBundle());

                } else {
                    startActivity(new Intent(gongxiangActivity.this, gongxiangtwoActivity.class));

                }
                break;
        }
    }
}

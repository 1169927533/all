package com.example.a11699.all.youhuafanhui;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.a11699.all.R;

import java.lang.reflect.Field;

/**
 * 作者：余智强
 * 2019/4/11
 */
public abstract class BaseActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {
    public final static String TAG = "zjc";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化滑动返回
     */
    private void initSwipeBackFinish() {
        Log.i(TAG, "初始化滑动返回");
        if (isSupportSwipeBack()) {
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
            //是32dp，现在给它改成0
            try {
                //属性
                Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                f_overHang.setAccessible(true);
                f_overHang.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //设置监听
            slidingPaneLayout.setPanelSlideListener(this);
            //setSliderFadeColor用来调节渐变色的，当调成透明的时候。就不会变化
            slidingPaneLayout.setSliderFadeColor(getResources().getColor(R.color.transparent));

            View leftview = new View(this);
            leftview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //在左边添加这个视图
            slidingPaneLayout.addView(leftview, 0);
            //获取到最顶层的视图容器
            ViewGroup decor = (ViewGroup) getWindow().getDecorView();
            //获取到右边的视图
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            //设置左边的视图为透明
            decorChild.setBackgroundColor(getResources().getColor(R.color.transparent));
            decor.removeView(decorChild);
            decor.addView(slidingPaneLayout);
            //在右边添加这个视图
            slidingPaneLayout.addView(decorChild, 1);
        }
    }

    /**
     * 是否支持滑动返回
     */
    protected boolean isSupportSwipeBack() {
        return true;
    }


    @Override
    public void onPanelSlide(@NonNull View view, float v) {
    }

    @Override
    public void onPanelOpened(@NonNull View view) {
        Log.i(TAG, "onPanelOpened执行了");
        finish();
    }

    @Override
    public void onPanelClosed(@NonNull View view) {
        Log.i(TAG, "onPanelClosed执行了");
    }
}






















package com.example.a11699.all.youhuafanhui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.a11699.all.R;

import java.lang.reflect.Field;

/**
 * 作者：余智强
 * 2019/4/12
 */
public class BaeTwo extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isSupportSlide()){
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            //修改SlidePaneLayout距离右边的距离
            try {
                Field field =  SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                //在改变私有属性的值时，这个Accessible必须设置为true
                field.setAccessible(true);
                //修改mOverhangSize的值为0
                field.set(savedInstanceState,0);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //给SlidingPanLayout设置右页面
            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView);
            //添加右面
            //1：先拿到现在视图的顶层页面
            ViewGroup decor = (ViewGroup) getWindow().getDecorView();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            decorChild.setBackgroundColor(getResources().getColor(R.color.transparent));
            decor.removeView(decorChild);
            decor.addView(slidingPaneLayout);
            slidingPaneLayout.addView(decorChild,1);
        }
    }
    protected boolean isSupportSlide(){
        return true;
    }

    @Override
    public void onPanelSlide(@NonNull View view, float v) {

    }

    @Override
    public void onPanelOpened(@NonNull View view) {

    }

    @Override
    public void onPanelClosed(@NonNull View view) {

    }
}

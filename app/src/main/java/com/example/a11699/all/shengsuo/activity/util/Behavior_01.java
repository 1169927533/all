package com.example.a11699.all.shengsuo.activity.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：余智强
 * 2019/4/17
 */
public class Behavior_01 extends CoordinatorLayout.Behavior<View> {
    //列表顶部和title底部重合时，列表的滑动距离
    private float deltaY;

    public Behavior_01(){

    }
    public Behavior_01(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 确定使用bahavior的View要依赖的View的类型
     * 我这监听的是Recycleview，当recycleview的状态发生改变的时候我要改变child的装态
     * @param parent 代表CoordingLayout
     * @param child  代表使用该Behavior的View
     * @param dependency 代表要监听的View
     * @return
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * 被依赖的view状态发生改变的回调
     * @param parent 代表CoordingLayout
     * @param child  代表使用该Behavior的view
     * @param dependency 代表要监听的view
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        Log.i("zjc","dependency.getY():"+dependency.getY()+"");//400 这个高度 = Recycleview上面Imageview的高度
        Log.i("zjc","child.getHeight():"+child.getHeight()+"");//100 这个高度 = TextView的高度
        Log.i("zjc","deltaY:"+deltaY+""); //0
        if(deltaY == 0){
            deltaY = dependency.getY()-child.getHeight();
            Log.i("zjc","deltaY == 0  deltaY:"+deltaY+""); //300
        }
        Log.i("zjc","deltaY:"+deltaY+"");
        float dy = dependency.getY() - child.getHeight();
        dy = dy<0?0:dy;
        Log.i("zjc","dy:"+dy+"");//300
        float y = -(dy/deltaY)*child.getHeight();
        Log.i("zjc","y:"+y+"");
        child.setTranslationY(y);
        return true;
    }
}

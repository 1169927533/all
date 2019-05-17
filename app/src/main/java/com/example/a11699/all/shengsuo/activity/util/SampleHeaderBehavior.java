package com.example.a11699.all.shengsuo.activity.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者：余智强
 * 2019/4/17
 */
public class SampleHeaderBehavior extends CoordinatorLayout.Behavior<TextView> {
    //界面整体向上滑动，达到列表可滑动的临界点
    private boolean upReach;
    //列表向上滑动后，在向下滑动，达到界面整体可滑动的零界点
    private boolean downReach;
    //列表上一个全部可见的item位置
    private int lastPosition = -1;
    public SampleHeaderBehavior() {
    }

    public SampleHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //是否拦截触摸事件
    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downReach = false;
                upReach = false;
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    //表示是否监听此次recycleview的滑动事件，这里我们只监听垂直方向上的滑动事件
    //axes表示横向或者纵向。
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    //嵌套滑动进行中要监听的子view将要滑动，滑动事件即将被消费（可以分配被谁消费）
    /**
     * 在嵌套滑动的子View未滑动之前告诉过来的准备滑动的情况
     * @param target 具体嵌套滑动的那个子类
     * @param dx 水平方向嵌套滑动的子View想要变化的距离
     * @param dy 垂直方向嵌套滑动的子View想要变化的距离   向下滑的时候这个dy是负的
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子View当前父View消耗的距离
     *                    consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子view做出相应的调整
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if(target instanceof RecyclerView){
            RecyclerView list = (RecyclerView) target;
            //列表第一个全部可见Item的位置 意思就是recycelview在滑动的时候，处于屏幕最上方的item是谁
            int pos = ((LinearLayoutManager)list.getLayoutManager()).findFirstVisibleItemPosition();
            Log.i("zjc","recycleview第一个可见的POS："+pos);
            if(pos == 0 && pos<lastPosition){
                downReach = true;
           }
            //整体可以滑动，否则recycleview、消费滑动事件
            if(canScroll(child,dy)&&pos == 0){
                float finalY = child.getTranslationY() - dy;
                if (finalY < -child.getHeight()) {
                    finalY = -child.getHeight();
                    upReach = true;
                } else if (finalY > 0) {
                    finalY = 0;
                }
                child.setTranslationY(finalY);
                // 让CoordinatorLayout消费滑动事件
                consumed[1] = dy;
            }
            lastPosition = pos;
            Log.i("zjc","lastPosition"+lastPosition+"");
        }
    }
    private boolean canScroll(View child, float scrollY) {
        //
        Log.i("zjc","scrollY:"+scrollY);
        Log.i("zjc","child.getTranslationY():"+child.getTranslationY());//向上滑动的距离 值为负数，顶部是-0
        Log.i("zjc","child.getHeight():"+child.getHeight());//400
        if (scrollY > 0 && child.getTranslationY() == -child.getHeight() && !upReach) {
            return false;
        }

        if (downReach) {
            return false;
        }
        return true;
    }
}
